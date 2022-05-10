package pl.allegro.logic.operators.arithmetic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientLogicOptIn
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

@OptIn(ClientLogicOptIn::class)
class DivisionOperationTest {

    @ParameterizedTest(name = "[{index}] DIVIDE operator - {0}")
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
                        4.divide(registryKey("test"))
                    },
                    expected = """{"/":[4,{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key and value",
                    expression = clientLogic {
                        registryKey("test").divide(6)
                    },
                    expected = """{"/":[{"var":"test"},6]}"""
                ),
                JsonLogicTestData(
                    testCase = "value and operation",
                    expression = clientLogic {
                        4.divide(registryKey("test").multiply(2))
                    },
                    expected = """{"/":[4,{"*":[{"var":"test"},2]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "keys",
                    expression = clientLogic {
                        registryKey("test").divide(registryKey("me"))
                    },
                    expected = """{"/":[{"var":"test"},{"var":"me"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "operation and key",
                    expression = clientLogic {
                        (registryKey("test").multiply(22)).divide(registryKey("me"))
                    },
                    expected = """{"/":[{"*":[{"var":"test"},22]},{"var":"me"}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
