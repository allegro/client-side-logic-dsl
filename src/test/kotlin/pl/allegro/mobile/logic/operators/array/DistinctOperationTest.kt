package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.ClientLogic.distinct
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class DistinctOperationTest {

    @ParameterizedTest(name = "[{index}] DISTINCT operator - {0}")
    @MethodSource("testData")
    fun `should map distinct operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "distinct on list of elements",
                expression = clientLogic {
                    listOfElements(registryKey("result0"), registryKey("result2")).distinct()
                },
                expected = """{"distinct":[{"var":"result0"},{"var":"result2"}]}"""
            ),
            JsonLogicTestData(
                testCase = "distinct on result of filter operation",
                expression = clientLogic {
                    listOfElements(registryKey("number1"), registryKey("number2"))
                        .filter { it.between(1, 10) }
                        .distinct()
                },
                expected = """{"distinct":{"filter":[[{"var":"number1"},{"var":"number2"}],{"<":[1,{"var":""},10]}]}}"""
            )
        ).toJsonLogicTestArgumentsStream()
    }
}
