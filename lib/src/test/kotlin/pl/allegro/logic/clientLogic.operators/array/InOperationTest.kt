package pl.allegro.logic.clientLogic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class InOperationTest {

    @ParameterizedTest(name = "[{index}] IN operator - {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "list",
                expression = clientLogic {
                    buildListOfElements {
                        add("John")
                        add("Paul")
                        add("George")
                        add("Ringo")
                    }.contains(registryKey("key"))
                },
                expected = """{"in":[ {"var":  "key"}, ["John", "Paul", "George", "Ringo"]]}"""
            ),
            JsonLogicTestData(
                testCase = "filtered list",
                expression = clientLogic {
                    buildListOfElements {
                        add("John")
                        add("Paul")
                        add("George")
                        add("Ringo")
                    }
                        .filter { it.containsString("R") }
                        .contains(registryKey("key"))
                },
                expected = """{"in":[{"var":"key"},{"filter":[["John","Paul","George","Ringo"],{"in":["R",{"var":""}]}]}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
