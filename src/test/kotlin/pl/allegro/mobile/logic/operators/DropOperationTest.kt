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
                    testCase = "extension from string, key",
                    expression = clientLogic {
                        registryKey("test").drop()
                    },
                    expected = """{"drop":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, result of lowercase operation",
                    expression = clientLogic {
                        registryKey("banana and strawberries").toLowercase().drop()
                    },
                    expected = """{"drop":{"lowercase":{"var":"bananaandstrawberries"}}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).drop()
                    },
                    expected = """{"drop":[{"var":"vegetables"},{"var":"fruits"}]}"""
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
                        list1.mergeWith(list2).drop()
                    },
                    expected = """{"drop":{"merge":[[1,2],[3,4]]}}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
