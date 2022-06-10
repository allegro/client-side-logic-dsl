package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments

class NoneOperationTest {

    @ParameterizedTest(name = "[{index}] NONE operator - {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "list, none",
                    expression = clientLogic {
                        listOfElements(registryKey("element1"), registryKey("element2"))
                            .none {
                                it.plus(2).isLessThan(registryKey("test"))
                            }
                    },
                    expected = """{"none":[
                        [{"var":"element1"},{"var":"element2"}],
                        {"<":[{"+":[{"var":""},2]},{"var":"test"}]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "list, map, none",
                    expression = clientLogic {
                        buildListOfElements {
                            add(1)
                            add(2)
                        }
                            .map { it.multiply(4) }
                            .none { it.isLessThan(2) }
                    },
                    expected = """{"none":[{"map":[[1,2],{"*":[{"var":""},4]}]},{"<":[{"var":""},2]}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
