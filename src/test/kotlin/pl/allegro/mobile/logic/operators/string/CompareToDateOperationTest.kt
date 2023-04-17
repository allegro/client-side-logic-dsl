package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream
import pl.allegro.mobile.logic.clientLogic

class CompareToDateOperationTest {

    @ParameterizedTest(name = "[{index}] COMPARE TO DATE operator - {0}")
    @MethodSource("testData")
    fun `should map compareToDate operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01", ComparePrecision.MILLISECOND)
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01","MILLISECOND"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(registryKey("date1"), ComparePrecision.MILLISECOND)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"var": "date1"},"MILLISECOND"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01", ComparePrecision.SECOND)
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01","SECOND"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(registryKey("date1"), ComparePrecision.SECOND)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"var": "date1"},"SECOND"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01", ComparePrecision.MINUTE)
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01","MINUTE"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(registryKey("date1"), ComparePrecision.MINUTE)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"var": "date1"},"MINUTE"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01", ComparePrecision.HOUR)
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01","HOUR"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(registryKey("date1"), ComparePrecision.HOUR)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"var": "date1"},"HOUR"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01", ComparePrecision.DAY)
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01","DAY"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(registryKey("date1"), ComparePrecision.DAY)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"var": "date1"},"DAY"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01", ComparePrecision.MONTH)
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01","MONTH"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(registryKey("date1"), ComparePrecision.MONTH)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"var": "date1"},"MONTH"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01", ComparePrecision.YEAR)
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01","YEAR"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(registryKey("date1"), ComparePrecision.YEAR)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"var": "date1"},"YEAR"]}"""
                ),
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate(concat("%s-01-01", registryKey("year") ), ComparePrecision.YEAR)
                    },
                    expected = """{"compareToDate": [{"var": "date"},{"cat":[{"var":"year"},"-01-01"]},"YEAR"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
