package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class ReplaceOperationTest {
    @ParameterizedTest(name = "[{index}] REPLACE operator - {0}")
    @MethodSource("testData")
    fun `should map Replace operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "from key, default count",
                    expression = clientLogic {
                        registryKey("key").replace(oldString = "old", newString = "new one")
                    },
                    expected = """{"replace":[{"var":"key"},"old","new one","all"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from key, exact count",
                    expression = clientLogic {
                        registryKey("test").replace(oldString = "old", newString = "new one", count = ReplaceCount.Exact(2))
                    },
                    expected = """{"replace":[{"var":"test"},"old","new one","2"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from key, all count",
                    expression = clientLogic {
                        registryKey("fruits").replace(oldString = "old", newString = "new one", count = ReplaceCount.All)
                    },
                    expected = """{"replace":[{"var":"fruits"},"old","new one","all"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from cat operation, default count",
                    expression = clientLogic {
                        concat("Delicious %s", registryKey("test")).replace(oldString = "banana", newString = "strawberry")
                    },
                    expected = """{"replace":[{"cat":["Delicious",{"var":"test"}]},"banana","strawberry","all"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from substr operation, exact count",
                    expression = clientLogic {
                        substring(registryKey("key"), startFromIndex = 2, numOfCharacters = 4).replace(
                            oldString = "banana",
                            newString = "strawberry",
                            count = ReplaceCount.Exact(6)
                        )
                    },
                    expected = """{"replace":[{"substr":[{"var":"key"},2,4]},"banana","strawberry","6"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from lowercase operation, all count",
                    expression = clientLogic {
                        lowercase(registryKey("someString")).replace(
                            oldString = "apple",
                            newString = "pear",
                            count = ReplaceCount.All
                        )
                    },
                    expected = """{"replace":[{"lowercase":{"var":"someString"}},"apple","pear","all"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
