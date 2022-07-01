package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class FindOperationTest {

    @ParameterizedTest(name = "[{index}] FIND operator - {0}")
    @MethodSource("testData")
    fun `should map find operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "extension with equal predicate",
                expression = clientLogic {
                    listOfElements(registryKey("test0"), registryKey("test1"))
                        .find { it.isEqual(registryKey("test3")) }
                },
                expected = """{"find":[[{"var":"test0"},{"var":"test1"}],{"==":[{"var":""},{"var":"test3"}]}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
