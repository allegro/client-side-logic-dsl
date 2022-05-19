package pl.allegro.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

class AllOperationTest {

    @ParameterizedTest(name = "[{index}] ALL operator - {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "greaterThan",
                expression = clientLogic {
                    buildListOfElements {
                        add(1)
                        add(2)
                        add(-1)
                    }.all { it.isGreaterThan(0) }
                },
                expected = """{"all":[[1,2,-1],{">":[{"var":""},0]}]}"""
            ),
            JsonLogicTestData(
                testCase = "filter all",
                expression = clientLogic {
                    buildListOfElements {
                        add(1)
                        add(2)
                        add(-1)
                    }.filter { it.isGreaterThan(0) }
                        .all { it.isGreaterThan(0) }
                },
                expected = """{"all":[{"filter":[[1,2,-1],{">":[{"var":""},0]}]},{">":[{"var":""},0]}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
