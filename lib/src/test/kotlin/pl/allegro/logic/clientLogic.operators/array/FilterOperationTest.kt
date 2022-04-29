package pl.allegro.logic.clientLogic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
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
