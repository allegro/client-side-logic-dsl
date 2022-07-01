package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class SortOperationTest {

    @ParameterizedTest(name = "[{index}] SORT operator - {0}")
    @MethodSource("testData")
    fun `should map sort operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "extension from list of keys, default mode",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).sort()
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, ascending mode",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).sort(mode = SortMode.Ascending)
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from list of keys, descending mode",
                    expression = clientLogic {
                        listOfElements(registryKey("vegetables"), registryKey("fruits")).sort(mode = SortMode.Descending)
                    },
                    expected = """{"sort":[[{"var":"vegetables"},{"var":"fruits"}],"desc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from some operation, default mode",
                    expression = clientLogic {
                        listOfElements(registryKey("flag0"), registryKey("flag2"))
                            .some { it.isEqual(true) }.sort()
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from some operation, ascending mode",
                    expression = clientLogic {
                        listOfElements(registryKey("flag0"), registryKey("flag2"))
                            .some { it.isEqual(true) }.sort(mode = SortMode.Ascending)
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"asc"]}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from some operation, descending mode",
                    expression = clientLogic {
                        listOfElements(registryKey("flag0"), registryKey("flag2"))
                            .some { it.isEqual(true) }.sort(mode = SortMode.Descending)
                    },
                    expected = """{"sort":[{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]},"desc"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
