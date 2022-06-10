package pl.allegro.mobile.logic.operators.arithmetic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class SubtractionOperationTest {

    @ParameterizedTest(name = "[{index}] MINUS (-) operator {0}")
    @MethodSource("testData")
    fun `should map minus operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "value and key",
                    expression = clientLogic {
                        4.minus(registryKey("test"))
                    },
                    expected = """{"-":[4,{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key and value",
                    expression = clientLogic {
                        registryKey("test").minus(6)
                    },
                    expected = """{"-":[{"var":"test"},6]}"""
                ),
                JsonLogicTestData(
                    testCase = "one param, key",
                    expression = clientLogic {
                        registryKey("test").negateNumber()
                    },
                    expected = """{"-":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "one param, operation",
                    expression = clientLogic {
                        registryKey("test")
                            .divide(3)
                            .negateNumber()
                    },
                    expected = """{"-":{"/":[{"var":"test"},3]}}"""
                ),
                JsonLogicTestData(
                    testCase = "one param, operation",
                    expression = clientLogic {
                        registryKey("test")
                            .divide(9)
                            .negateNumber()
                            .plus(2)
                    },
                    expected = """{"+": [{"-":{"/":[{"var":"test"},9]}}, 2]}"""
                ),
                JsonLogicTestData(
                    testCase = "MINUS operator (chained)",
                    expression = clientLogic {
                        8.minus(registryKey("test")).minus(2)
                    },
                    expected = """{"-":[{"-":[8, {"var":"test"}]}, 2]}"""
                ),
                JsonLogicTestData(
                    testCase = "MINUS and PLUS operator (chained)",
                    expression = clientLogic {
                        8.plus(registryKey("test")).minus(2)
                    },
                    expected = """{"-":[{"+":[8, {"var":"test"}]}, 2]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
