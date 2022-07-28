package pl.allegro.mobile.logic.operators.string

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
                testCase = "extension, unmodified width and exact length",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(width = FormatLength.Unmodified, decimalPlaces = FormatLength.Exact(2))
                },
                expected = """{"decimalFormat":["%.2f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "unmodified width and exact length",
                expression = clientLogic {
                    decimalFormat(element = registryKey("someString0"), width = FormatLength.Unmodified, decimalPlaces = FormatLength.Exact(7))
                },
                expected = """{"decimalFormat":["%.7f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "extension, exact width and exact length",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(width = FormatLength.Exact(3), decimalPlaces = FormatLength.Exact(0))
                },
                expected = """{"decimalFormat":["%3.0f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "extension, unmodified width and unmodified length",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(width = FormatLength.Exact(7), decimalPlaces = FormatLength.Unmodified)
                },
                expected = """{"decimalFormat":["%7f",{"var":"someString0"}]}"""
            ),
            JsonLogicTestData(
                testCase = "extension, unmodified width and unmodified length",
                expression = clientLogic {
                    registryKey("someString0").formatDecimal(width = FormatLength.Unmodified, decimalPlaces = FormatLength.Unmodified)
                },
                expected = """{"decimalFormat":["%f",{"var":"someString0"}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
