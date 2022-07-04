package pl.allegro.mobile.logic.operators

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import java.util.stream.Stream

class DropOperationTest {

    @ParameterizedTest(name = "[{index}] DROP operator - {0}")
    @MethodSource("testData")
    fun `should map drop operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "extension from string, key, default mode",
                    expression = clientLogic {
                        registryKey("test").drop(1)
                    },
                    expected = """{"drop":[{"var":"test"},1,"first"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, key, last mode",
                    expression = clientLogic {
                        registryKey("test").drop(count = 2, mode = DropMode.Last)
                    },
                    expected = """{"drop":[{"var":"test"},2,"last"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, key, first mode",
                    expression = clientLogic {
                        registryKey("test").drop(count = 3, mode = DropMode.First)
                    },
                    expected = """{"drop":[{"var":"test"},3,"first"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, result of lowercase operation",
                    expression = clientLogic {
                        registryKey("fruits").toLowercase().drop(2)
                    },
                    expected = """{"drop":[{"lowercase":{"var":"fruits"}},2,"first"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).drop(1)
                    },
                    expected = """{"drop":[[{"var":"vegetables"},{"var":"fruits"}],1,"first"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, default mode",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).drop(1)
                    },
                    expected = """{"drop":[[{"var":"vegetables"},{"var":"fruits"}],1,"first"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, last mode",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).drop(count = 1, mode = DropMode.Last)
                    },
                    expected = """{"drop":[[{"var":"vegetables"},{"var":"fruits"}],1,"last"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, first mode",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).drop(1, mode = DropMode.First)
                    },
                    expected = """{"drop":[[{"var":"vegetables"},{"var":"fruits"}],1,"first"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list, result of merge operation",
                    expression = clientLogic {
                        val list1 = buildListOfElements {
                            add(1)
                            add(2)
                        }
                        val list2 = buildListOfElements {
                            add(3)
                            add(4)
                        }
                        list1.mergeWith(list2).drop(2)
                    },
                    expected = """{"drop":[{"merge":[[1,2],[3,4]]},2,"first"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
