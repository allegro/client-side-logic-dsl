package pl.allegro.logic.operators.dataaccess

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientLogicOptIn
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class MissingSomeOperationTest {

    @ParameterizedTest(name = "[{index}] MISSING SOME operator - {0}")
    @MethodSource("testData")
    fun `should map MissingSome operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "vararg",
                expression = clientLogic {
                    missingSome(requiredCount = 2, "a", "b", "c")
                },
                expected = """{"missing_some":[2, ["a", "b", "c"]]}"""
            ),
            JsonLogicTestData(
                testCase = "list of keys (String)",
                expression = clientLogic {
                    missingSome(requiredCount = 2, registryKeys = listOf("a", "b", "c"))
                },
                expected = """{"missing_some":[2, ["a", "b", "c"]]}"""
            ),
            JsonLogicTestData(
                testCase = "listOfElements",
                expression = clientLogic {
                    val elements = buildListOfElements {
                        add("a")
                        add("b")
                        add("c")
                    }
                    missingSome(requiredCount = 2, elements)
                },
                expected = """{"missing_some":[2, ["a", "b", "c"]]}"""
            ),
            JsonLogicTestData(
                testCase = "operation result",
                expression = clientLogic {
                    val expression = If(registryKey("A").isNotNull()) {
                        buildListOfElements {
                            add("a1")
                            add("a2")
                        }
                    }.Else {
                        emptyListOfElements()
                    }
                    missingSome(requiredCount = 2, expression)
                },
                expected = """{"missing_some":[2,{"if":[{"!=":[{"var":"A"},null]},["a1","a2"],[]]}]}"""
            ),
            JsonLogicTestData(
                testCase = "if, missingSome, missing",
                expression = clientLogic {
                    If(
                        missing("first_name", "last_name")
                            .mergeWith(missingSome(requiredCount = 1, "cell_phone", "home_phone"))
                    ) {
                        "We_require_first_name,_last_name,_and_one_phone_number."
                    }.Else {
                        "OK_to_proceed"
                    }
                },
                expected = """{"if":[
                        |{"merge":[
                        |{"missing":["first_name", "last_name"]},
                        |{"missing_some":[1, ["cell_phone", "home_phone"]]}]
                        |},
                        |"We_require_first_name,_last_name,_and_one_phone_number.", 
                        |"OK_to_proceed"]}""".trimMargin()

            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
