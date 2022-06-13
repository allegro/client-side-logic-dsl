package pl.allegro.mobile.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class StrictNotEqualOperationTest {

    @ParameterizedTest(name = "[{index}] STRICT NOT EQUAL (!==) operator - {0}")
    @MethodSource("testData")
    fun `should map StrictNotEqual operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "key, string",
                expression = clientLogic {
                    registryKey("key1").isStrictNotEqual("false")
                },
                expected = """{ "!==" : [{"var":"key1"}, "false"]}"""
            ),
            JsonLogicTestData(
                testCase = "key, boolean",
                expression = clientLogic {
                    registryKey("key1").isStrictNotEqual(false)
                },
                expected = """{ "!==" : [{"var":"key1"}, false]}"""
            ),
            JsonLogicTestData(
                testCase = "key, int",
                expression = clientLogic {
                    registryKey("key1").isStrictNotEqual(2)
                },
                expected = """{ "!==" : [{"var":"key1"}, 2]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
