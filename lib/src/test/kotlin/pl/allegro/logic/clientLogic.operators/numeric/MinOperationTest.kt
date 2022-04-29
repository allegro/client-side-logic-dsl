package pl.allegro.logic.clientLogic.operators.numeric

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

@OptIn(ClientLogicOptIn::class)
class MinOperationTest {

    @ParameterizedTest(name = "[{index}] MIN operator - {0}")
    @MethodSource("testData")
    fun `should map Min operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "listOfElements",
                    expression = clientLogic {
                        buildListOfElements {
                            add(registryKey("key1"))
                            add(2)
                            add(3)
                        }.min()
                    },
                    expected = """{"min":[{"var":"key1"},2,3]}"""
                ),
                JsonLogicTestData(
                    testCase = "registry",
                    expression = clientLogic {
                        listOfElements(registryKey("key1"), registryKey("key2")).min()
                    },
                    expected = """{"min":[{"var":"key1"},{"var":"key2"}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
