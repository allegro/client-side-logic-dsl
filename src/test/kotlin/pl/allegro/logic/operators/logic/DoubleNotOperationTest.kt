package pl.allegro.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

class DoubleNotOperationTest {

    @ParameterizedTest(name = "[{index}] DOUBLE NOT (!!) operator - {0}")
    @MethodSource("testData")
    fun `should map DoubleNot operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "cast",
                expression = clientLogic {
                    registryKey("key1").castToBoolean()
                },
                expected = """{ "!!" : {"var":"key1"}}"""
            ),
            JsonLogicTestData(
                testCase = "cast and not",
                expression = clientLogic {
                    registryKey("key1").castToBoolean().not()
                },
                expected = """{"!":{"!!":{"var":"key1"}}}"""
            ),
            JsonLogicTestData(
                testCase = "cast and not - list with number",
                expression = clientLogic {
                    buildListOfElements { add(5) }.castToBoolean().not()
                },
                expected = """{"!":{"!!":[5]}}"""
            ),
            JsonLogicTestData(
                testCase = "cast and not - empty list",
                expression = clientLogic { listOfElements().castToBoolean().not() },
                expected = """{"!":{"!!":[]}}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
