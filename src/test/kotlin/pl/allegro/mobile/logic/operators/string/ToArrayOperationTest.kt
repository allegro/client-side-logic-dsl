package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.ClientLogic.distinct
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class ToArrayOperationTest {

    @ParameterizedTest(name = "[{index}] TOARRAY operator - {0}")
    @MethodSource("testData")
    fun `should map toArray operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "extension from string, key",
                    expression = clientLogic {
                        registryKey("test").toCharArray()
                    },
                    expected = """{"toArray":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, chaining",
                    expression = clientLogic {
                        registryKey("test").toCharArray().distinct()
                    },
                    expected = """{"distinct":{"toArray":{"var":"test"}}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, result of uppercase operation",
                    expression = clientLogic {
                        registryKey("fruits").toUppercase().toCharArray()
                    },
                    expected = """{"toArray":{"uppercase":{"var":"fruits"}}}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
