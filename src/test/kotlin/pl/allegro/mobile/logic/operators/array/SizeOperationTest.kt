package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class SizeOperationTest {

    @ParameterizedTest(name = "[{index}] SIZE operator - {0}")
    @MethodSource("testData")
    fun `should map size operation to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "size on list of elements",
                expression = clientLogic {
                    listOfElements(registryKey("flag0"), registryKey("flag2")).size()
                },
                expected = """{"size":[{"var":"flag0"},{"var":"flag2"}]}"""
            ),
            JsonLogicTestData(
                testCase = "size of list of elements", 
                expression = clientLogic {
                    sizeOf(listOfElements(registryKey("flag0"), registryKey("flag2")))
                },
                expected = """{"size":[{"var":"flag0"},{"var":"flag2"}]}"""
            ),
            JsonLogicTestData(
                testCase = "size on result of filter operation",
                expression = clientLogic {
                    listOfElements(registryKey("number1"), registryKey("number2"))
                        .filter { it.between(1, 10) }
                        .size()
                },
                expected = """{"size":{"filter":[[{"var":"number1"},{"var":"number2"}],{"<":[1,{"var":""},10]}]}}"""
            ),
            JsonLogicTestData(
                testCase = "size of result of filter operation",
                expression = clientLogic {
                    sizeOf(listOfElements(registryKey("number1"), registryKey("number2")).none { it.between(2, 3) })
                },
                expected = """{"size":{"none":[[{"var":"number1"},{"var":"number2"}],{"<":[2,{"var":""},3]}]}}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
