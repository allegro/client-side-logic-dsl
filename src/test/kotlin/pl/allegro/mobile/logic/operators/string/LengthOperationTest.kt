package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class LengthOperationTest {

    @ParameterizedTest(name = "[{index}] LENGTH operator - {0}")
    @MethodSource("testData")
    fun `should map concat operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "length on string element",
                    expression = clientLogic {
                        registryKey("flag1").length()
                    },
                    expected = """{"length":{"var":"flag1"}}"""
                ),
                JsonLogicTestData(
                    testCase = "length of string element", //
                    expression = clientLogic {
                        lengthOf(registryKey("flag0"))
                    },
                    expected = """{"length":{"var":"flag0"}}"""
                ),
                JsonLogicTestData(
                    testCase = "length on result of concat operation",
                    expression = clientLogic {
                        concat("%s!%s", registryKey("number1"), registryKey("number2")).length()
                    },
                    expected = """{"length":{"cat":[{"var":"number1"},"!",{"var":"number2"}]}}"""
                ),
                JsonLogicTestData(
                    testCase = "length of result of substring operation",
                    expression = clientLogic {
                        lengthOf(substring(registryKey("key"), startFromIndex = 2, numOfCharacters = 4))
                    },
                    expected = """{"length":{"substr":[{"var":"key"},2,4]}}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
