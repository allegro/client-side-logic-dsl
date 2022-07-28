package pl.allegro.mobile.logic.operators.numeric

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class DecimalFormatOperationTest {

    @ParameterizedTest(name = "[{index}] DecimalFormat operator - {0}")
    @MethodSource("testData")
    fun `should map decimalFormat operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected(stripWhitespaces = false)
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "extension, default width and exact decimal places",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(decimalPlaces = DecimalFormatLength.Exact(2))
                },
                expected = """{"decimalFormat":["%.2f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "extension, unmodified width and exact decimal places",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(
                        minWidth = DecimalFormatLength.Unmodified,
                        decimalPlaces = DecimalFormatLength.Exact(2)
                    )
                },
                expected = """{"decimalFormat":["%.2f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "unmodified width and exact decimal places",
                expression = clientLogic {
                    decimalFormat(
                        element = registryKey("someString0"),
                        minWidth = DecimalFormatLength.Unmodified,
                        decimalPlaces = DecimalFormatLength.Exact(7)
                    )
                },
                expected = """{"decimalFormat":["%.7f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "default width and exact decimal places",
                expression = clientLogic {
                    decimalFormat(element = registryKey("someString0"), decimalPlaces = DecimalFormatLength.Exact(7))
                },
                expected = """{"decimalFormat":["%.7f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "extension, exact width and exact decimal places",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(
                        minWidth = DecimalFormatLength.Exact(3),
                        decimalPlaces = DecimalFormatLength.Exact(0)
                    )
                },
                expected = """{"decimalFormat":["%3.0f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "extension, unmodified width and unmodified decimal places",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(
                        minWidth = DecimalFormatLength.Exact(7),
                        decimalPlaces = DecimalFormatLength.Unmodified
                    )
                },
                expected = """{"decimalFormat":["%7f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "extension, unmodified width and unmodified decimal places",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(
                        minWidth = DecimalFormatLength.Unmodified,
                        decimalPlaces = DecimalFormatLength.Unmodified
                    )
                },
                expected = """{"decimalFormat":["%f",{"var":"someString0"}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
