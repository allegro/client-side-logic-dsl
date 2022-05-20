package pl.allegro.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

class FilterOperationTest {

    @ParameterizedTest(name = "[{index}] FILTER operator - {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "with equal",
                expression = clientLogic {
                    listOfElements(registryKey("test0"), registryKey("test1"))
                        .filter { it.isEqual(registryKey("test3")) }
                },
                expected = """{"filter":[[{"var":"test0"},{"var":"test1"}],{"==":[{"var":""},{"var":"test3"}]}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
