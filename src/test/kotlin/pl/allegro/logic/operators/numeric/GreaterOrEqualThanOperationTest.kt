package pl.allegro.logic.operators.numeric

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientLogicOptIn
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

@OptIn(ClientLogicOptIn::class)
class GreaterOrEqualThanOperationTest {

    @ParameterizedTest(name = "[{index}] GREATER OR EQUAL operator - {0}")
    @MethodSource("testData")
    fun `should map GreaterOrEqual operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "key, int",
                    expression = clientLogic {
                        registryKey("temp").isGreaterOrEqual(0)
                    },
                    expected = """{ ">=" : [{"var":"temp"}, 0]}"""
                ),
                JsonLogicTestData(
                    testCase = "int, key",
                    expression = clientLogic {
                        55.isGreaterOrEqual(registryKey("test"))
                    },
                    expected = """{">=":[55,{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, operator",
                    expression = clientLogic {
                        val allLessThanTwo = buildListOfElements {
                            add(1)
                            add(registryKey("test"))
                        }.all { it.isLessThan(2) }
                        registryKey("test")
                            .isGreaterOrEqual(If(allLessThanTwo) { 3 }.Else { 5 })
                    },
                    expected = """{">=":[
                            {"var":"test"},
                            {"if":[{"all":[[1,{"var":"test"}],{"<":[{"var":""},2]}]},3,5]}
                        ]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
