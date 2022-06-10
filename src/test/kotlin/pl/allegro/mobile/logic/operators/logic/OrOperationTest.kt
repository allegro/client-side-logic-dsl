package pl.allegro.mobile.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class OrOperationTest {

    @ParameterizedTest(name = "[{index}] OR operator - {0}")
    @MethodSource("testData")
    fun `should map Or operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "two keys",
                expression = clientLogic {
                    registryKey("key11").or(registryKey("key22"))
                },
                expected = """{ "or" : [{"var":"key11"}, {"var":"key22"}]}"""
            ),
            JsonLogicTestData(
                testCase = "find first truthy element (4 params)",
                expression = clientLogic {
                    registryKey("key1")
                        .or(registryKey("key2"))
                        .or(registryKey("key3"))
                        .or(registryKey("key4"))
                },
                expected = """{ "or" : [{"var":"key1"}, {"var":"key2"}, {"var":"key3"}, {"var":"key4"}]}"""
            ),
            JsonLogicTestData(
                testCase = "find first truthy element - array",
                expression = clientLogic {
                    or(
                        listOfElements(
                            registryKey("key1"),
                            registryKey("key2"),
                            registryKey("key3")
                        )
                    )
                },
                expected = """{ "or" : [{"var":"key1"}, {"var":"key2"}, {"var":"key3"}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
