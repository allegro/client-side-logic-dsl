package pl.allegro.logic.clientLogic.operators.demo

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class FormValidationOperatorsTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    fun `should map form validation operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "Login and password are not empty",
                expression = clientLogic {
                    registryKey(LOGIN).isNotEmpty()
                        .and(registryKey(PASSWORD).isNotEmpty())
                },
                expected = """{"and":[{"if":[{"var":"$LOGIN"},true,false]},{"if":[{"var":"$PASSWORD"},true,false]}]}"""
            ),
            JsonLogicTestData(
                testCase = "Login and password are not empty and checkbox is checked",
                expression = clientLogic {
                    registryKey(LOGIN).isNotEmpty()
                        .and(registryKey(PASSWORD).isNotEmpty())
                        .and(registryKey(CHECKBOX_KEY).isEqual(CHECKED))
                },
                expected = """{"and":[{"and":[{"if":[{"var":"userLogin"},true,false]},{"if":[{"var":"pass"},true,false]}]},{"==":[{"var":"consentCheckbox"},"checked"]}]}"""
            ),
            JsonLogicTestData(
                testCase = "Login does not contain @ and it at most 12 characters - does not work in app",
                expression = clientLogic {
                    val login = registryKey(LOGIN)
                    (login.containsString("@").not())
                        .and(login.lengthIsAtMost(12))
                },
                expected = """{"and":[{"!":{"in":["@",{"var":"userLogin"}]}},{"if":[{"substr":[{"var":"userLogin"},12]},false,true]}]}"""
            ),
            JsonLogicTestData(
                testCase = "Login contains @ and it at most 12 characters - does not work in app",
                expression = clientLogic {
                    val login = registryKey(LOGIN)
                    login.containsString("@")
                        .and(login.lengthIsAtMost(12))
                },
                expected = """{"and":[{"in":["@",{"var":"userLogin"}]},{"if":[{"substr":[{"var":"userLogin"},12]},false,true]}]}"""
            ),
            JsonLogicTestData(
                testCase = "LOGIN_AND_PASS_NOT_EMPTY example - missing, if",
                expression = clientLogic {
                    If(missing(LOGIN, PASSWORD).not()) {
                        true
                    }.Else {
                        false
                    }
                },
                expected = """{"if":[{"!":{"missing":["$LOGIN","$PASSWORD"]}},true,false]}"""
            ),
            JsonLogicTestData(
                testCase = "LOGIN_AND_PASS_NOT_EMPTY example - missing, isNullOrEmpty",
                expression = clientLogic {
                    missing(LOGIN, PASSWORD).isEmpty()
                },
                expected = """{"if":[{"missing":["$LOGIN","$PASSWORD"]},false, true]}"""
            ),
            JsonLogicTestData(
                testCase = "PASSWORD_VALID example",
                expression = clientLogic {
                    registryKey(PASSWORD).lengthIsAtLeast(minLength = 7)
                },
                expected = """{"if":[{"substr":[{"var":"pass"},6]},true,false]}"""
            ),
            JsonLogicTestData(
                testCase = "PASSWORD_VALID example - substring",
                expression = clientLogic {
                    substring(registryKey(PASSWORD), 6).isNotEmpty()
                },
                expected = """{"if":[{"substr":[{"var":"$PASSWORD"},6]},true,false]}"""
            ),
            JsonLogicTestData(
                testCase = "WELCOME_MESSAGE example",
                expression = clientLogic {
                    If(registryKey(LOGIN).isNotEmpty()) {
                        concat("Welcome %s!", registryKey(LOGIN))
                    }.Else {
                        "Please enter your login"
                    }
                },
                expected = """{"if":[
                {"if":[{"var":"$LOGIN"},true,false]},
                {"cat":["Welcome",{"var":"$LOGIN"},"!"]},
                "Please enter your login"]}"""
            ),
            JsonLogicTestData(
                testCase = "WELCOME_MESSAGE example - nested if",
                expression = clientLogic {
                    val login = registryKey(LOGIN)
                    If(login.isNotEmpty()) {
                        If(login.containsString("VIP")) {
                            "Welcome VIP!"
                        }.Else {
                            concat("Welcome %s!", registryKey(LOGIN))
                        }
                    }.Else {
                        "Please enter your login"
                    }
                },
                expected = """{"if":[
                |{"if":[{"var":"$LOGIN"},true,false]},
                |{"if":[{"in":["VIP",{"var":"$LOGIN"}]},"WelcomeVIP!",{"cat":["Welcome",{"var":"$LOGIN"},"!"]}]},
                |"Please enter your login"]}""".trimMargin()
            ),
        ).toJsonLogicTestArgumentsStream()

        private const val LOGIN = "userLogin"
        private const val PASSWORD = "pass"
        private const val CHECKBOX_KEY = "consentCheckbox"
        private const val CHECKED = "checked"
    }
}
