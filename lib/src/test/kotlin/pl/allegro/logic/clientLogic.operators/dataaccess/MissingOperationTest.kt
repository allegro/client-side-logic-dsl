package pl.allegro.mobile.dsl.mbox.clientLogic.operators.dataaccess

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.dsl.mbox.core.annotations.ClientLogicOptIn
import pl.allegro.mobile.dsl.mbox.clientLogic.clientLogic

import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class MissingOperationTest {

    @ParameterizedTest(name = "[{index}] MISSING operator - {0}")
    @MethodSource("testData")
    fun `should map Missing operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "vararg",
                expression = clientLogic {
                    missing("key1", "key2", "key3")
                },
                expected = """{"missing":["key1", "key2", "key3"]}"""
            ),
            JsonLogicTestData(
                testCase = "list of keys",
                expression = clientLogic {
                    missing(registryKeys = listOf("key1", "key2", "key3"))
                },
                expected = """{"missing":["key1", "key2", "key3"]}"""
            ),
            JsonLogicTestData(
                testCase = "expression",
                expression = clientLogic {
                    val expression = If(registryKey("A")){
                        buildListOfElements {
                            add("a1")
                            add("a2")
                        }
                    }.Else {
                        emptyListOfElements()
                    }
                    missing(expression)
                },
                expected = """{"missing":{"if":[{"var":"A"},["a1","a2"],[]]}}"""
            ),
            JsonLogicTestData(
                testCase = "if",
                expression = clientLogic {
                    If(missing("login", "password")) {
                        "NotEnough"
                    }.Else {
                        "OkToProceed"
                    }
                },
                expected = """{"if":[{"missing":["login", "password"]},"NotEnough","OkToProceed"]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
