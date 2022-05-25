package pl.allegro.logic.operators.numeric

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class LessOrEqualThanOperationTest {

    @ParameterizedTest(name = "[{index}] LESS OR EQUAL THAN (<=) - {0}")
    @MethodSource("testData")
    fun `should map LessOrEqualThan operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "key, value",
                    expression = clientLogic {
                        registryKey("temp").isLessOrEqualThan(4)
                    },
                    expected = """{ "<=" : [{"var":"temp"}, 4]}"""
                ),
                JsonLogicTestData(
                    testCase = "int, operation",
                    expression = clientLogic {
                        8.isLessOrEqualThan(registryKey("test").plus(99))
                    },
                    expected = """{"<=":[8,{"+":[{"var":"test"},99]}]}"""
                ),
                JsonLogicTestData(
                    testCase = "key, operation",
                    expression = clientLogic {
                        registryKey("test1")
                            .isLessOrEqualThan(registryKey("test2").divide(2))
                    },
                    expected = """{"<=":[{"var":"test1"},{"/":[{"var":"test2"},2]}]}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
