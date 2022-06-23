package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
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
                    testCase = "from key, default character, default mode",
                    expression = clientLogic {
                        trim(registryKey("key"))
                    },
                    expected = """{"trim":[{"var":"key"}," ","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from key, specified character, default mode",
                    expression = clientLogic {
                        registryKey("key").trimmed('/')
                    },
                    expected = """{"trim":[{"var":"key"},"/","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from key, default character, default mode",
                    expression = clientLogic {
                        registryKey("key").trimmed()
                    },
                    expected = """{"trim":[{"var":"key"}," ","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from key, default character, start mode",
                    expression = clientLogic {
                        registryKey("key").trimmed(trimMode = TrimMode.START)
                    },
                    expected = """{"trim":[{"var":"key"}," ","start"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from key, default character, bothEnds mode",
                    expression = clientLogic {
                        registryKey("key").trimmed(trimMode = TrimMode.BOTH_ENDS)
                    },
                    expected = """{"trim":[{"var":"key"}," ","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from key, default character, end mode",
                    expression = clientLogic {
                        registryKey("key").trimmed(trimMode = TrimMode.END)
                    },
                    expected = """{"trim":[{"var":"key"}," ","end"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from key, default character, start mode",
                    expression = clientLogic {
                        trim(registryKey("key"), trimMode = TrimMode.START)
                    },
                    expected = """{"trim":[{"var":"key"}," ","start"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from key, default character, bothEnds mode",
                    expression = clientLogic {
                        trim(registryKey("key"), trimMode = TrimMode.BOTH_ENDS)
                    },
                    expected = """{"trim":[{"var":"key"}," ","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from key, default character, end mode",
                    expression = clientLogic {
                        trim(registryKey("key"), trimMode = TrimMode.END)
                    },
                    expected = """{"trim":[{"var":"key"}," ","end"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from cat operation, character specified, default mode",
                    expression = clientLogic {
                        trim(concat("Delicious %s", registryKey("test")), '/')
                    },
                    expected = """{"trim":[{"cat":["Delicious",{"var":"test"}]},"/","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from substr operation, default character, default mode",
                    expression = clientLogic {
                        trim(substring(registryKey("key"), startFromIndex = 2, numOfCharacters = 4))
                    },
                    expected = """{"trim":[{"substr":[{"var":"key"},2,4]},"","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from cat operation, specified character, default mode",
                    expression = clientLogic {
                        concat("Delicious %s", registryKey("test")).trimmed('/')
                    },
                    expected = """{"trim":[{"cat":["Delicious",{"var":"test"}]},"/","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from substr operation, default character, default mode",
                    expression = clientLogic {
                        substring(registryKey("key"), startFromIndex = 2, numOfCharacters = 4).trimmed()
                    },
                    expected = """{"trim":[{"substr":[{"var":"key"},2,4]},"","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from capitalize operation, default character, start mode",
                    expression = clientLogic {
                        registryKey("someString").capitalized().trimmed(trimMode = TrimMode.START)
                    },
                    expected = """{"trim":[{"capitalize":{"var":"someString"}},"","start"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from lowercase operation, default character, bothEnds mode",
                    expression = clientLogic {
                        lowercase(registryKey("someString")).trimmed(trimMode = TrimMode.BOTH_ENDS)
                    },
                    expected = """{"trim":[{"lowercase":{"var":"someString"}},"","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from capitalize operation, default character, end mode",
                    expression = clientLogic {
                        registryKey("someString").capitalized().trimmed(trimMode = TrimMode.END)
                    },
                    expected = """{"trim":[{"capitalize":{"var":"someString"}},"","end"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from lowercase operation, default character, start mode",
                    expression = clientLogic {
                        trim(lowercase(registryKey("someString")), trimMode = TrimMode.START)
                    },
                    expected = """{"trim":[{"lowercase":{"var":"someString"}},"","start"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from capitalize operation, default character, bothEnds mode",
                    expression = clientLogic {
                        trim(registryKey("someString").capitalized(), trimMode = TrimMode.BOTH_ENDS)
                    },
                    expected = """{"trim":[{"capitalize":{"var":"someString"}},"","bothEnds"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from lowercase operation, default character, end mode",
                    expression = clientLogic {
                        trim(lowercase(registryKey("someString")), trimMode = TrimMode.END)
                    },
                    expected = """{"trim":[{"lowercase":{"var":"someString"}},"","end"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
