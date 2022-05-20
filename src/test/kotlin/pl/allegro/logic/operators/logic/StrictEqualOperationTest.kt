package pl.allegro.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

class StrictEqualOperationTest {

    @ParameterizedTest(name = "[{index}] STRICT EQUAL (===) operator - {0}")
    @MethodSource("testData")
    fun `should map StrictEqual operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "key, value",
                expression = clientLogic {
                    registryKey("key1").strictEqual(true)
                },
                expected = """{ "===" : [{"var":"key1"}, true]}"""
            ),
            JsonLogicTestData(
                testCase = "value, expression",
                expression = clientLogic {
                    val ifExpression = If(registryKey("key1")) { "a" }.Else { "b" }
                    ifExpression.strictEqual("a")
                },
                expected = """{"===":[{"if":[{"var":"key1"},"a","b"]}, "a"]}"""
            ),
            JsonLogicTestData(
                testCase = "key, number",
                expression = clientLogic {
                    registryKey("A").strictEqual(2.22)
                },
                expected = """{"===":[{"var":"A"},2.22]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
