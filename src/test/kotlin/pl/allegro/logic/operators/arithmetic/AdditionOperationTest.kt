package pl.allegro.logic.operators.arithmetic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class AdditionOperationTest {

    @ParameterizedTest(name = "[{index}] PLUS operator - {0}")
    @MethodSource("testData")
    fun `should map arithmetic operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "value and key",
                    expression = clientLogic {
                        4.plus(registryKey("test"))
                    },
                    expected = """{"+":[4, {"var":"test"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key and value",
                    expression = clientLogic {
                        registryKey("test").plus(6.1)
                    },
                    expected = """{"+":[{"var":"test"},6.1]}"""
                ),
                JsonLogicTestData(
                    testCase = "two keys",
                    expression = clientLogic {
                        registryKey("test").plus(registryKey("test2"))
                    },
                    expected = """{"+":[{"var":"test"},{"var":"test2"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "chained",
                    expression = clientLogic {
                        1.plus(registryKey("key1"))
                            .plus(6)
                    },
                    expected = """{"+":[1,{"var":"key1"},6]}"""
                ),
                JsonLogicTestData(
                    testCase = "chained with parenthesis",
                    expression = clientLogic {
                        1.plus(
                            registryKey("key1").plus(6).plus(9)
                        )
                    },
                    expected = """{"+":[1,{"var":"key1"},6,9]}"""
                ),
                JsonLogicTestData(
                    testCase = "sum()",
                    expression = clientLogic {
                        buildListOfElements {
                            add(1)
                            add(registryKey("A"))
                            add(6)
                            add(registryKey("B"))
                        }.sum()
                    },
                    expected = """{"+":[1,{"var":"A"},6,{"var":"B"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "cast to number (one parameter)`",
                    expression = clientLogic {
                        registryKey("test").castToNumber()
                    },
                    expected = """{"+":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "cast to number (cancatenated string)`",
                    expression = clientLogic {
                        concat("%s111", registryKey("test")).castToNumber()
                    },
                    expected = """{"+":{"cat":[{"var":"test"},"111"]}}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
