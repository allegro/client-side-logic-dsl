package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class MatchOperationTest {
    @ParameterizedTest(name = "[{index}] MATCH operator - {0}")
    @MethodSource("testData")
    fun `should map Match operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "from key, character specified, default mode",
                    expression = clientLogic {
                        registryKey("123").match("^[0-9]+$")
                    },
                    expected = """{"match":[{"var":"123"},"^[0-9]+$"]}"""
                ),
                JsonLogicTestData(
                    testCase = "from key, character specified, default mode",
                    expression = clientLogic {
                        registryKey("123").match("")
                    },
                    expected = """{"match":[{"var":"123"},""]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
