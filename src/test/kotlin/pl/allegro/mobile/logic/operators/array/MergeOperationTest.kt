package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class MergeOperationTest {

    @ParameterizedTest(name = "[{index}] MERGE operator - {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "two lists",
                expression = clientLogic {
                    val list1 = buildListOfElements {
                        add(1)
                        add(2)
                    }
                    val list2 = buildListOfElements {
                        add(3)
                        add(4)
                    }
                    list1.mergeWith(list2)
                },
                expected = """{"merge":[ [1,2], [3,4] ]}"""
            ),
            JsonLogicTestData(
                testCase = "operator and list",
                expression = clientLogic {
                    buildListOfElements {
                        add(3)
                        add(4)
                    }.filter { it.isEqual(4) }
                        .mergeWith(buildListOfElements {
                            add(5)
                            add(6)
                        })
                },
                expected = """{"merge":[{"filter":[[3,4],{"==":[{"var":""},4]}]},[5,6]]}"""
            ),
            JsonLogicTestData(
                testCase = "missing",
                expression = clientLogic {
                    val ifExpression =
                        If(registryKey("financing")) {
                            buildListOfElements {
                                add("apr")
                                add("term")
                            }
                        }.Else {
                            emptyListOfElements()
                        }
                    missing(buildListOfElements { add("vim") }.mergeWith(ifExpression))
                },
                expected = """{"missing": { "merge": [["vim"], {"if": [{"var":"financing"}, ["apr", "term"], [] ]} ]}}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
