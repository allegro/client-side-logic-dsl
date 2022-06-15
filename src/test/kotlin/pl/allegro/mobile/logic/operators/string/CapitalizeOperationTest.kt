package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class CapitalizeOperationTest {

    @ParameterizedTest(name = "[{index}] CAPITALIZE operator - {0}")
    @MethodSource("testData")
    fun `should map capitalize operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "capitalize on result of var operation",
                expression = clientLogic {
                    capitalize(registryKey("someString0"))
                },
                expected = """{"capitalize":{"var":"someString0"}}"""
            ),
            JsonLogicTestData(
                testCase = "capitalization of result of var operation",
                expression = clientLogic {
                    registryKey("someString0").capitalized()
                },
                expected = """{"capitalize":{"var":"someString0"}}"""
            ),
            JsonLogicTestData(
                testCase = "capitalize on result of concat operation",
                expression = clientLogic {
                    concat("%s!%s", registryKey("test"), registryKey("name")).capitalized()
                },
                expected = """{"capitalize":{"cat":[{"var":"test"},"!",{"var":"name"}]}}"""
            ),
            JsonLogicTestData(
                testCase = "capitalization of result of substring operation",
                expression = clientLogic {
                    capitalize(substring(registryKey("key"), startFromIndex = 2, numOfCharacters = 4))
                },
                expected = """{"capitalize":{"substr":[{"var":"key"},2,4]}}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}