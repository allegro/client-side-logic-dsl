package pl.allegro.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class ConcatOperationTest {

    @ParameterizedTest(name = "[{index}] CONCAT operator - {0}")
    @MethodSource("testData")
    fun `should map concat operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "string, key",
                    expression = clientLogic {
                        concat("Delicious %s", registryKey("test"))
                    },
                    expected = """{"cat":["Delicious",{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, number",
                    expression = clientLogic {
                        concat("%s111", registryKey("test"))
                    },
                    expected = """{"cat":[{"var":"test"}, "111"]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, string, key",
                    expression = clientLogic {
                        concat("%s!%s", registryKey("test"), registryKey("name"))
                    },
                    expected = """{"cat":[{"var":"test"},"!", {"var":"name"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "too much %s",
                    expression = clientLogic {
                        concat("Delicious %s pie%s!", registryKey("A"))
                    },
                    expected = """{"cat":["Delicious",{"var":"A"},"pie", "!"]}"""
                ),
                JsonLogicTestData(
                    testCase = "Too little %s",
                    expression = clientLogic {
                        concat("Delicious %s pie", registryKey("A"), registryKey("B"))
                    },
                    expected = """{"cat":["Delicious", {"var":"A"}, "pie"]}"""
                ),
                JsonLogicTestData(
                    testCase = "no %s",
                    expression = clientLogic {
                        concat("Delicious", registryKey("A"))
                    },
                    expected = """"Delicious""""
                ),
                JsonLogicTestData(
                    testCase = "only %s",
                    expression = clientLogic {
                        concat("%s", registryKey("A"))
                    },
                    expected = """{"var":"A"}"""
                ),
                JsonLogicTestData(
                    testCase = "concat with operator",
                    expression = clientLogic {
                        val result = registryKey("test").plus(2)
                        concat("Delicious %s pie", result)
                    },
                    expected = """{"cat":["Delicious",{"+":[{"var":"test"},2]},"pie"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
