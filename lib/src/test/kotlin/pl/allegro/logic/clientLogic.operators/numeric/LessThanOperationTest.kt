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
class LessThanOperationTest {

    @ParameterizedTest(name = "[{index}] LESS THAN (<) operator - {0}")
    @MethodSource("testData")
    fun `should map LessThan operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "key, int",
                    expression = clientLogic {
                        registryKey("key1").isLessThan(5)
                    },
                    expected = """{ "<" : [{"var":"key1"}, 5]}"""
                ),
                JsonLogicTestData(
                    testCase = "value, key",
                    expression = clientLogic {
                        5.isLessThan(registryKey("key1"))
                    },
                    expected = """{ "<" : [5, {"var":"key1"}]}"""
                ),
                JsonLogicTestData(
                    testCase = "chained, plus",
                    expression = clientLogic {
                        9.plus(registryKey("test1"))
                            .isLessThan(registryKey("test2").plus(3))
                    },
                    expected = """{"<":[{"+":[9,{"var":"test1"}]},{"+":[{"var":"test2"},3]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "int, key",
                    expression = clientLogic {
                        9.isLessThan(registryKey("test").plus(2))
                    },
                    expected = """{"<":[9, {"+":[{"var":"test"},2]}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
