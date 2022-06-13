package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class ContainsStringOperationTest {

    @ParameterizedTest(name = "[{index}] Contains string (IN) operator - {0}")
    @MethodSource("testData")
    fun `should map ContainsString operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "value, key",
                    expression = clientLogic {
                        "Springfield".containsString(registryKey("test"))
                    },
                    expected = """{"in":[{"var":"test"},"Springfield"]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, string",
                    expression = clientLogic {
                        registryKey("test").containsString("a")
                    },
                    expected = """{"in":["a",{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "concat operator and key",
                    expression = clientLogic {
                        concat("Springfield%s", registryKey("test1"))
                            .containsString(registryKey("test2"))
                    },
                    expected = """{"in":[{"var":"test2"},{"cat":["Springfield",{"var":"test1"}]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "two operators",
                    expression = clientLogic {
                        concat("Springfield%s", registryKey("test1"))
                            .containsString(concat("%s!", registryKey("test2")))
                    },
                    expected = """{"in":[{"cat":[{"var":"test2"},"!"]},{"cat":["Springfield",{"var":"test1"}]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "operator, string",
                    expression = clientLogic {
                        concat("%s!", registryKey("test"))
                            .containsString("b")
                    },
                    expected = """{"in":["b",{"cat":[{"var":"test"},"!"]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "long chain - makes no sense but should compile",
                    expression = clientLogic {
                        registryKey("test")
                            .containsString("a")
                            .containsString("tr")
                            .containsString("ue")
                            .containsString("t")
                    },
                    expected = """{"in":["t",{"in":["ue",{"in":["tr",{"in":["a",{"var":"test"}]}]}]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "not, contains",
                    expression = clientLogic {
                        registryKey("login").not().containsString("")
                    },
                    expected = """{"in":["",{"!":{"var":"login"}}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
