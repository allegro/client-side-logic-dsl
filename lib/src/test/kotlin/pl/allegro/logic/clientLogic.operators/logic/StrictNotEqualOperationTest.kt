package pl.allegro.logic.clientLogic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
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
                    registryKey("key1").strictNotEqual("false")
                },
                expected = """{ "!==" : [{"var":"key1"}, "false"]}"""
            ),
            JsonLogicTestData(
                testCase = "key, boolean",
                expression = clientLogic {
                    registryKey("key1").strictNotEqual(false)
                },
                expected = """{ "!==" : [{"var":"key1"}, false]}"""
            ),
            JsonLogicTestData(
                testCase = "key, int",
                expression = clientLogic {
                    registryKey("key1").strictNotEqual(2)
                },
                expected = """{ "!==" : [{"var":"key1"}, 2]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
