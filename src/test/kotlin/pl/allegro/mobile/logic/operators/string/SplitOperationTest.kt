package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class SplitOperationTest {
    @ParameterizedTest(name = "[{index}] SPLIT operator - {0}")
    @MethodSource("testData")
    fun `should map Split operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "split on string element with one delimiter",
                    expression = clientLogic {
                        registryKey("one,two,three").split(",")
                    },
                    expected = """{"split":[{"var":"one,two,three"},[","]]}"""
                ),
                JsonLogicTestData(
                    testCase = "split on string element with two delimiters",
                    expression = clientLogic {
                        registryKey("one,two,three").split(",", ";")
                    },
                    expected = """{"split":[{"var":"one,two,three"},[",", ";"]]}"""
                ),
                JsonLogicTestData(
                    testCase = "split on string element without delimiter",
                    expression = clientLogic {
                        registryKey("one,two,three").split()
                    },
                    expected = """{"split":[{"var":"one,two,three"},[]]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
