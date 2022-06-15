package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class LowercaseOperationTest {

    @ParameterizedTest(name = "[{index}] LOWERCASE operator - {0}")
    @MethodSource("testData")
    fun `should map lowercase operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "lowercase on result of var operation",
                expression = clientLogic {
                    lowercase(registryKey("someString0"))
                },
                expected = """{"lowercase":{"var":"someString0"}}"""
            ),
            JsonLogicTestData(
                testCase = "lowercased result of var operation",
                expression = clientLogic {
                    registryKey("someString0").toLowercase()
                },
                expected = """{"lowercase":{"var":"someString0"}}"""
            ),
            JsonLogicTestData(
                testCase = "lowercase on result of concat operation",
                expression = clientLogic {
                    concat("%s!%s", registryKey("test"), registryKey("name")).toLowercase()
                },
                expected = """{"lowercase":{"cat":[{"var":"test"},"!",{"var":"name"}]}}"""
            ),
            JsonLogicTestData(
                testCase = "lowercased result of substring operation",
                expression = clientLogic {
                    lowercase(substring(registryKey("key"), startFromIndex = 2, numOfCharacters = 4))
                },
                expected = """{"lowercase":{"substr":[{"var":"key"},2,4]}}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}