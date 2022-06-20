package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class UppercaseOperationTest {

    @ParameterizedTest(name = "[{index}] Uppercase operator - {0}")
    @MethodSource("testData")
    fun `should map uppercase operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "uppercase on result of var operation",
                expression = clientLogic {
                    uppercase(registryKey("someString0"))
                },
                expected = """{"uppercase":{"var":"someString0"}}"""
            ),
            JsonLogicTestData(
                testCase = "uppercased result of var operation",
                expression = clientLogic {
                    registryKey("someString0").toUppercase()
                },
                expected = """{"uppercase":{"var":"someString0"}}"""
            ),
            JsonLogicTestData(
                testCase = "uppercase on result of concat operation",
                expression = clientLogic {
                    concat("%s!%s", registryKey("test"), registryKey("name")).toUppercase()
                },
                expected = """{"uppercase":{"cat":[{"var":"test"},"!",{"var":"name"}]}}"""
            ),
            JsonLogicTestData(
                testCase = "uppercased result of substring operation",
                expression = clientLogic {
                    uppercase(substring(registryKey("key"), startFromIndex = 2, numOfCharacters = 4))
                },
                expected = """{"uppercase":{"substr":[{"var":"key"},2,4]}}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
