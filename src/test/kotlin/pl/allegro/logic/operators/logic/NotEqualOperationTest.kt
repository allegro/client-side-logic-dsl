package pl.allegro.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientLogicOptIn
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class NotEqualOperationTest {

    @ParameterizedTest(name = "[{index}] NOT EQUAL (!=) operator - {0}")
    @MethodSource("testData")
    fun `should map NotEqual operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "key, string",
                expression = clientLogic {
                    registryKey("key1").isNotEqual("test")
                },
                expected = """{ "!=" : [{"var":"key1"}, "test"]}"""
            ),
            JsonLogicTestData(
                testCase = "key, boolean",
                expression = clientLogic {
                    registryKey("key1").isNotEqual(false)
                },
                expected = """{ "!=" : [{"var":"key1"}, false]}"""
            ),
            JsonLogicTestData(
                testCase = "key, number",
                expression = clientLogic {
                    registryKey("key1").isNotEqual(66.666)
                },
                expected = """{ "!=" : [{"var":"key1"}, 66.666]}"""
            ),
            JsonLogicTestData(
                testCase = "two keys",
                expression = clientLogic {
                    registryKey("test").isNotEqual(registryKey("key1"))
                },
                expected = """{ "!=" : [{"var":"test"}, {"var":"key1"}]}"""
            ),
            JsonLogicTestData(
                testCase = "isNotNull",
                expression = clientLogic {
                    registryKey("test").isNotNull()
                },
                expected = """{"!=":[{"var":"test"},null]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
