package pl.allegro.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientLogicOptIn
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class NotOperationTest {

    @ParameterizedTest(name = "[{index}] NOT (!) operator - {0}")
    @MethodSource("testData")
    fun `should map Not operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "one operator",
                expression = clientLogic {
                    registryKey("key1").not()
                },
                expected = """{ "!" : {"var":"key1"}}"""
            ),
            JsonLogicTestData(
                testCase = "two not operators should be merged into one",
                expression = clientLogic {
                    registryKey("key1").not().not()
                },
                expected = """{ "!!" : {"var":"key1"}}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
