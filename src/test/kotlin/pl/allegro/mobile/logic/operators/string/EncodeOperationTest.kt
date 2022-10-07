package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class EncodeOperationTest {

    @ParameterizedTest(name = "[{index}] ENCODE operator - {0}")
    @MethodSource("testData")
    fun `should map encode operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "encode on string element",
                    expression = clientLogic {
                        registryKey("flag1").encode()
                    },
                    expected = """{"encode":{"var":"flag1"}}"""
                ),
                JsonLogicTestData(
                    testCase = "encode on string element", //
                    expression = clientLogic {
                        encodeOf(registryKey("flag0"))
                    },
                    expected = """{"encode":{"var":"flag0"}}"""
                ),
                JsonLogicTestData(
                    testCase = "encode on result of concat operation",
                    expression = clientLogic {
                        concat("%s!%s", registryKey("number1"), registryKey("number2")).encode()
                    },
                    expected = """{"encode":{"cat":[{"var":"number1"},"!",{"var":"number2"}]}}"""
                )
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
