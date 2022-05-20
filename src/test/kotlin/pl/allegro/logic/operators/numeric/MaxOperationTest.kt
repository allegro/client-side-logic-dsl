package pl.allegro.logic.operators.numeric

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

class MaxOperationTest {

    @ParameterizedTest(name = "[{index}] MAX operator - {0}")
    @MethodSource("testData")
    fun `should map Max operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "list of elements",
                expression = clientLogic {
                    val expression = registryKey("A").plus(2)
                    buildListOfElements {
                        add(1)
                        add(3)
                        add(expression)
                    }.max()
                },
                expected = """{"max":[1,3,{"+":[{"var":"A"},2]}]}"""
            ),
            JsonLogicTestData(
                testCase = "only one value from registry",
                expression = clientLogic {
                    listOfElements(registryKey("test")).max()
                },
                expected = """{"max":{"var":"test"}}"""
            ),
            JsonLogicTestData(
                testCase = "only registry",
                expression = clientLogic {
                    listOfElements(registryKey("A"), registryKey("B")).max()
                },
                expected = """{"max":[{"var":"A"}, {"var":"B"}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
