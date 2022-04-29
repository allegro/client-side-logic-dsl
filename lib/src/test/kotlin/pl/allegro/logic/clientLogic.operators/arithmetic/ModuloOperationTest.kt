package pl.allegro.logic.clientLogic.operators.arithmetic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

@OptIn(ClientLogicOptIn::class)
class ModuloOperationTest {

    @ParameterizedTest(name = "[{index}] MODULO operator - {0}")
    @MethodSource("testData")
    fun `should map arithmetic operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "value and key",
                    expression = clientLogic {
                        67.modulo(registryKey("test"))
                    },
                    expected = """{"%":[67,{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key and value",
                    expression = clientLogic {
                        registryKey("test")
                            .modulo(2)
                    },
                    expected = """{"%":[{"var":"test"},2]}"""
                ),
                JsonLogicTestData(
                    testCase = "key and number",
                    expression = clientLogic {
                        registryKey("test")
                            .modulo(8)
                    },
                    expected = """{"%":[{"var":"test"},8]}"""
                ),
                JsonLogicTestData(
                    testCase = "chained",
                    expression = clientLogic {
                        registryKey("test")
                            .plus(2)
                            .modulo(8)
                    },
                    expected = """{"%":[{"+":[{"var":"test"},2]},8]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
