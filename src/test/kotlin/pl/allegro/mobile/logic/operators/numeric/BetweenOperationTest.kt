package pl.allegro.mobile.logic.operators.numeric

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class BetweenOperationTest {

    @ParameterizedTest(name = "[{index}] BETWEEN (<) operation - {0}")
    @MethodSource("testData")
    fun `should map between operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "ints",
                    expression = clientLogic {
                        registryKey("temp").between(min = 0, max = 100)
                    },
                    expected = """{ "<": [0, {"var":"temp"}, 100]}"""
                ),
                JsonLogicTestData(
                    testCase = "min from registry, max int",
                    expression = clientLogic {
                        registryKey("temp").between(min = registryKey("min"), max = 100)
                    },
                    expected = """{ "<": [{"var":"min"}, {"var":"temp"}, 100]}"""
                ),
                JsonLogicTestData(
                    testCase = "min int, max from registry",
                    expression = clientLogic {
                        registryKey("temp").between(min = 100, max = registryKey("max"))
                    },
                    expected = """{ "<": [100, {"var":"temp"}, {"var":"max"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "min and max operations",
                    expression = clientLogic {
                        registryKey("temp").between(
                            min = 100.minus(registryKey("test")),
                            max = registryKey("max").plus(3)
                        )
                    },
                    expected = """{"<":[{"-":[100,{"var":"test"}]},{"var":"temp"},{"+":[{"var":"max"},3]}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
