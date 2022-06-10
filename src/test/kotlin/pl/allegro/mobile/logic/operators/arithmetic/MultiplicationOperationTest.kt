package pl.allegro.mobile.logic.operators.arithmetic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class MultiplicationOperationTest {

    @ParameterizedTest(name = "[{index}] MULTIPLY operator - {0}")
    @MethodSource("testData")
    fun `should map arithmetic operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "value, key",
                    expression = clientLogic {
                        4.multiply(registryKey("test"))
                    },
                    expected = """{"*":[4,{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, value",
                    expression = clientLogic {
                        registryKey("test").multiply(2)
                    },
                    expected = """{"*":[{"var":"test"}, 2]}"""
                ),
                JsonLogicTestData(
                    testCase = "two keys",
                    expression = clientLogic {
                        registryKey("test")
                            .multiply(registryKey("test2"))
                    },
                    expected = """{"*":[{"var":"test"}, {"var":"test2"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "chained",
                    expression = clientLogic {
                        2.multiply(registryKey("test")).multiply(6)
                    },
                    expected = """{"*":[2, {"var":"test"}, 6]}"""
                ),
                JsonLogicTestData(
                    testCase = "chained (nested)",
                    expression = clientLogic {
                        2.multiply(registryKey("test"))
                            .multiply(6.multiply(registryKey("test")))
                    },
                    expected = """{"*":[2, {"var":"test"}, 6, {"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "with plus",
                    expression = clientLogic {
                        2.plus(registryKey("test"))
                            .multiply(6.multiply(registryKey("test")))
                    },
                    expected = """{"*":[{"+":[2,{"var":"test"}]},6,{"var":"test"}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
