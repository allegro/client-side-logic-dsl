package pl.allegro.logic.clientLogic.operators.numeric

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
class GreaterThanOperationTest {

    @ParameterizedTest(name = "[{index}] GREATER THAN operator - {0}")
    @MethodSource("testData")
    fun `should map GreaterThan operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "key, int",
                    expression = clientLogic {
                        registryKey("key1").isGreaterThan(5)
                    },
                    expected = """{ ">" : [{"var":"key1"}, 5]}"""
                ),
                JsonLogicTestData(
                    testCase = "two keys",
                    expression = clientLogic {
                        registryKey("key1").isGreaterThan(registryKey("key2"))
                    },
                    expected = """{ ">" : [{"var":"key1"}, {"var":"key2"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "int, operation",
                    expression = clientLogic {
                        5.isGreaterThan(99.divide(registryKey("test")))
                    },
                    expected = """{">":[5,{"/":[99,{"var":"test"}]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "value, key",
                    expression = clientLogic {
                        5.isGreaterThan(registryKey("test"))
                    },
                    expected = """{">":[5,{"var":"test"}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
