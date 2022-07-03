package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments

class JoinToStringOperationTest {

    @ParameterizedTest(name = "[{index}] JOINTOSTRING operator - {0}")
    @MethodSource("testData")
    fun `should map joinToString operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "extension from list of keys, all default",
                    expression = clientLogic {
                        listOfElements(registryKey("a"), registryKey("b")).joinToString()
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, changed prefix",
                    expression = clientLogic {
                        listOfElements(registryKey("tree"), registryKey("fruit")).joinToString(prefix = "<")
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, changed postfix",
                    expression = clientLogic {
                        listOfElements(registryKey("a"), registryKey("b")).joinToString(postfix = ">")
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, changed limit",
                    expression = clientLogic {
                        listOfElements(registryKey("a"), registryKey("b")).joinToString(limit = 2)
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, changed truncated",
                    expression = clientLogic {
                        listOfElements(registryKey("greeting"), registryKey("mode")).joinToString(truncated = "!!!")
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from some operation, all default",
                    expression = clientLogic {
                        listOfElements(registryKey("flag0"), registryKey("flag2"))
                            .some { it.isEqual(true) }.joinToString()
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from map operation, changed prefix",
                    expression = clientLogic {
                        buildListOfElements {
                            add(1)
                            add(2)
                            add(registryKey("test"))
                        }.map { it.multiply(2) }.joinToString(prefix = "answer: ")
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from none operation, changed postfix",
                    expression = clientLogic {
                        listOfElements(registryKey("element1"), registryKey("element2"))
                            .none {
                                it.plus(2).isLessThan(registryKey("test"))
                            }.joinToString(postfix = "!")
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"desc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from distinct operation, changed limit",
                    expression = clientLogic {
                        listOfElements(registryKey("number1"), registryKey("number2"))
                            .distinct().joinToString(limit = 10)
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"desc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from some operation, changed truncated",
                    expression = clientLogic {
                        listOfElements(registryKey("flag0"), registryKey("flag2"))
                            .some { it.isEqual("my_test") }.joinToString(truncated = "??")
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"desc"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
