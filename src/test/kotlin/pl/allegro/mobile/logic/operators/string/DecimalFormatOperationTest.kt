package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class DecimalFormatOperationTest {

    @ParameterizedTest(name = "[{index}] Format operator - {0}")
    @MethodSource("testData")
    fun `should map format operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected(stripWhitespaces = false)
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "format result of var operation",
                expression = clientLogic {
                    registryKey("someString0").format(registryKey("someString0"))
                },
                expected = """{"decimalFormat":[{"var":"someString0"},[{"var":"someString0"}]]}"""
            ),
            JsonLogicTestData(
                testCase = "format string with elements as params",
                expression = clientLogic {
                    format("%f test %f", registryKey("someString0"), registryKey("someString1"))
                },
                expected = """{"decimalFormat":["%f test %f",[{"var":"someString0"},{"var":"someString1"}]]}"""
            ),
            JsonLogicTestData(
                testCase = "format string with unsupported simple types",
                expression = clientLogic {
                    format("%s test %d %s", "someString", 100, true, registryKey("someString0"))
                },
                expected = """{"decimalFormat":["%s test %d %s",[100,{"var":"someString0"}]]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
