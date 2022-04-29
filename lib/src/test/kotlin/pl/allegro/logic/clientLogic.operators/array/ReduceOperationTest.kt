package pl.allegro.logic.clientLogic.operators.array

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
class ReduceOperationTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "initial int",
                    expression = clientLogic {
                        listOfElements(registryKey("test0"), registryKey("test1"))
                            .reduce(initialValue = 0) { current, accumulator ->
                                current.plus(accumulator)
                            }
                    },
                    expected = """{"reduce":[
                        |[{"var":"test0"},{"var":"test1"}],
                        |{"+":[{"var":"current"},{"var":"accumulator"}]},0]}""".trimMargin()
                ),
                JsonLogicTestData(
                    testCase = "initial boolean",
                    expression = clientLogic {
                        listOfElements(registryKey("A"), registryKey("B"))
                            .reduce(initialValue = true) { current, accumulator ->
                                current.and(accumulator)
                            }
                    },
                    expected = """{"reduce":[
                        |[{"var":"A"},{"var":"B"}],
                        |{"and":[{"var":"current"},{"var":"accumulator"}]},true]}""".trimMargin()
                ),
                JsonLogicTestData(
                    testCase = "initial string",
                    expression = clientLogic {
                        listOfElements(registryKey("A"), registryKey("B"))
                            .reduce(initialValue = "TEST") { current, accumulator ->
                                concat("%s,%s", accumulator, current)
                            }
                    },
                    expected = """{"reduce":[
                        |[{"var":"A"},{"var":"B"}],
                        |{"cat":[{"var":"accumulator"},",",{"var":"current"}]},
                        |"TEST"]}""".trimMargin()
                ),
                JsonLogicTestData(
                    testCase = "map, reduce",
                    expression = clientLogic {
                        listOfElements(registryKey("test0"), registryKey("test1"))
                            .map { it.multiply(2) }
                            .reduce(initialValue = 0) { current, accumulator ->
                                current.plus(accumulator)
                            }
                    },
                    expected = """{"reduce":[
                        |{"map":[[{"var":"test0"},{"var":"test1"}],{"*":[{"var":""},2]}]},
                        |{"+":[{"var":"current"},{"var":"accumulator"}]},0]}""".trimMargin()
                ),
                JsonLogicTestData(
                    testCase = "default initial value",
                    expression = clientLogic {
                        listOfElements(registryKey("test0"), registryKey("test1"))
                            .reduce { current, accumulator ->
                                current.plus(accumulator).plus(2)
                            }
                    },
                    expected = """{"reduce":[
                        [{"var":"test0"},{"var":"test1"}],
                        {"+":[{"var":"current"},{"var":"accumulator"},2]},0]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
