package pl.allegro.mobile.logic.operators.string

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream
import java.util.stream.Stream

class ToArrayOperationTest {

    @ParameterizedTest(name = "[{index}] TOARRAY operator - {0}")
    @MethodSource("testData")
    fun `should map toArray operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData(): Stream<Arguments?>? {
            return listOf(
                JsonLogicTestData(
                    testCase = "from string, key",
                    expression = clientLogic {
                        asArray(registryKey("test"))
                    },
                    expected = """{"toArray":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, key",
                    expression = clientLogic {
                        registryKey("test").toArray()
                    },
                    expected = """{"toArray":{"var":"test"}}"""
                ),
                JsonLogicTestData(
                    testCase = "from string, result of concat operation",
                    expression = clientLogic {
                        asArray(concat("Delicious %s", registryKey("melon")))
                    },
                    expected = """{"toArray":{"cat":["Delicious",{"var":"melon"}]}}"""
                ),
                JsonLogicTestData(
                    testCase = "extension from string, result of uppercase operation",
                    expression = clientLogic {
                        registryKey("fruits").toUppercase().toArray()
                    },
                    expected = """{"toArray":{"uppercase":{"var":"fruits"}}}"""
                ),
            ).toJsonLogicTestArgumentsStream()
        }
    }
}
