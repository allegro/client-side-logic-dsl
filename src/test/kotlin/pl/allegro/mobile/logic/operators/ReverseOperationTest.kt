package pl.allegro.mobile.logic.operators

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import java.util.stream.Stream

class ReverseOperationTest {

    @ParameterizedTest(name = "[{index}] REVERSE operator - {0}")
    @MethodSource("testData")
    fun `should map reverse operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "from string, key",
                    expression = clientLogic {
                        reverse(registryKey("test"))
                    },
                    expected = """{"reverse":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, key",
                    expression = clientLogic {
                        registryKey("test").reversed()
                    },
                    expected = """{"reverse":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "from string, result of concat operation",
                    expression = clientLogic {
                        reverse(concat("Delicious %s", registryKey("melon")))
                    },
                    expected = """{"reverse":{"cat":["Delicious",{"var":"melon"}]}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, result of lowercase operation",
                    expression = clientLogic {
                        registryKey("banana and strawberries").toLowercase().reversed()
                    },
                    expected = """{"reverse":{"lowercase":{"var":"bananaandstrawberries"}}}"""
                ),
                JsonLogicTestData(
                    testCase = "from list of keys",
                    expression = clientLogic {
                        reverse(listOfElements(registryKey("vegetables"), registryKey("mushrooms"), registryKey("fruits")))
                    },
                    expected = """{"reverse":[{"var":"vegetables"},{"var":"mushrooms"},{"var":"fruits"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).reversed()
                    },
                    expected = """{"reverse":[{"var":"vegetables"},{"var":"fruits"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "from list, result of map operation",
                    expression = clientLogic {
                        reverse(buildListOfElements {
                            add(3)
                            add(13)
                            add(registryKey("test"))
                        }.map { it.multiply(2) })
                    },
                    expected = """{"reverse":{"map":[[3,13,{"var":"test"}],{"*":[{"var":""},2]}]}}"""
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
                        list1.mergeWith(list2).reversed()
                    },
                    expected = """{"reverse":{"merge":[[1,2],[3,4]]}}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
