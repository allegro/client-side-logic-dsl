package pl.allegro.logic.clientLogic.operators.numeric

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

@OptIn(ClientLogicOptIn::class)
class BetweenOrEqualOperationTest {

    @ParameterizedTest(name = "[{index}] BETWEEN OR EQUAL operator - {0}")
    @MethodSource("testData")
    fun `should map between or equal operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "key, min: double, max: key",
                    expression = clientLogic {
                        registryKey("temp").betweenOrEqual(min = 0.1, max = registryKey("max"))
                    },
                    expected = """{ "<=": [0.1, {"var":"temp"}, {"var":"max"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, min: key, max: int",
                    expression = clientLogic {
                        registryKey("temp").betweenOrEqual(min = registryKey("min"), max = 99)
                    },
                    expected = """{"<=":[{"var":"min"},{"var":"temp"},99]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, min: int, max: int",
                    expression = clientLogic {
                        registryKey("temp").betweenOrEqual(min = 0, max = 100)
                    },
                    expected = """{ "<=": [0, {"var":"temp"}, 100]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, min: int, max: operation",
                    expression = clientLogic {
                        registryKey("temp").betweenOrEqual(min = 0, max = 100.plus(registryKey("test")))
                    },
                    expected = """{"<=":[0,{"var":"temp"},{"+":[100,{"var":"test"}]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, min: operation, max: key",
                    expression = clientLogic {
                        registryKey("temp").betweenOrEqual(
                            min = If(registryKey("test")) { 1 }.Else { 3},
                            max = registryKey("test")
                        )
                    },
                    expected = """{"<=":[{"if":[{"var":"test"},1,3]},{"var":"temp"},{"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "operation, min: key, max: value",
                    expression = clientLogic {
                        (2.plus(registryKey("test"))).betweenOrEqual(
                            min = registryKey("test1"),
                            max = 100
                        )
                    },
                    expected = """{"<=":[{"var":"test1"},{"+":[2,{"var":"test"}]},100]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
