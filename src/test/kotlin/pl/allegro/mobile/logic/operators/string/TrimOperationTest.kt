package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.ClientLogic.endsWith
import pl.allegro.mobile.logic.ClientLogic.startsWith
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class TrimOperationTest {
    @ParameterizedTest(name = "[{index}] TRIM operator - {0}")
    @MethodSource("testData")
    fun `should map Trim operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "from key, character specified, default mode",
                    expression = clientLogic {

                        trim(registryKey("key"), '/')
                    },
                    expected = """{"trim":[{"var":"key"},"/","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from operation, numOfCharacters not specified",
                    expression = clientLogic {
                        val concatenatedString = concat("%sjsonlogic", registryKey("test"))
                        substring(concatenatedString, startFromIndex = 4)
                    },
                    expected = """{"substr":[{"cat":[{"var":"test"},"jsonlogic"]},4]}"""
                ),
                JsonLogicTestData(
                    testCase = "from operation, numOfCharacters specified",
                    expression = clientLogic {
                        val ifExpression = If(registryKey("test")) { "JsonLogic" }.Else { "JS" }
                        substring(ifExpression, startFromIndex = 4, numOfCharacters = -2)
                    },
                    expected = """{"substr":[{"if":[{"var":"test"},"JsonLogic","JS"]},4,-2]}"""
                ),
                JsonLogicTestData(
                    testCase = "value starts with string",
                    expression = clientLogic {
                        registryKey("A").startsWith("test")
                    },
                    expected = """{"==":[{"substr":[{"var":"A"},0,4]},"test"]}"""
                ),
                JsonLogicTestData(
                    testCase = "value starts with string",
                    expression = clientLogic {
                        registryKey("A").endsWith("logic")
                    },
                    expected = """{"==":[{"substr":[{"var":"A"},-5]},"logic"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }

}