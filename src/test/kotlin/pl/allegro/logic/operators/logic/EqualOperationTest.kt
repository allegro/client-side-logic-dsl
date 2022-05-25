package pl.allegro.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

class EqualOperationTest {

    @ParameterizedTest(name = "[{index}] EQUAL (==) operator - {0}")
    @MethodSource("testData")
    fun `should map equal operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "two keys",
                expression = clientLogic {
                    registryKey("key1").isEqual(registryKey("key2"))
                },
                expected = """{ "==" : [{"var":"key1"}, {"var":"key2"}]}"""
            ),
            JsonLogicTestData(
                testCase = "key, operator - ==",
                expression = clientLogic {
                    registryKey("A")
                        .isEqual(
                            registryKey("B").plus(2)
                        )
                },
                expected = """{"==":[{"var":"A"},{"+":[{"var":"B"},2]}]}"""
            ),
            JsonLogicTestData(
                testCase = "value, operation",
                expression = clientLogic {
                    registryKey("key2")
                        .plus(1)
                        .isEqual(3)
                },
                expected = """{"==":[{"+":[{"var":"key2"}, 1]}, 3]}"""
            ),
            JsonLogicTestData(
                testCase = "key, value",
                expression = clientLogic {
                    registryKey("test").isEqual("Hello")
                },
                expected = """{"==":[{"var":"test"},"Hello"]}"""
            ),
            JsonLogicTestData(
                testCase = "isNull",
                expression = clientLogic {
                    registryKey("test").isNull()
                },
                expected = """{"==":[{"var":"test"},null]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
