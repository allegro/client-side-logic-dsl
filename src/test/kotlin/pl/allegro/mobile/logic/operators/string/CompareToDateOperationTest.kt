package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream
import pl.allegro.mobile.logic.clientLogic

class CompareToDateOperationTest {

    @ParameterizedTest(name = "[{index}] COMPARE TO DATE operator - {0}")
    @MethodSource("testData")
    fun `should map compareToDate operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "",
                    expression = clientLogic {
                        registryKey("date").compareToDate("2022-02-01")
                    },
                    expected = """{"compareToDate": [{"var": "date"},"2022-02-01"]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}