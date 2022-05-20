package pl.allegro.logic.operators.dataaccess

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientLogicOptIn
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class
DataAccessTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    fun `should map data access operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "VAR operator (access data from registry)",
                expression = clientLogic {
                    registryKey("key1")
                },
                expected = """{"var":"key1"}"""
            ),
            JsonLogicTestData(
                testCase = "VAR operator - string default",
                expression = clientLogic {
                    registryKey("key1", default = "defaultString")
                },
                expected = """{"var":["key1", "defaultString"]}"""
            ),
            JsonLogicTestData(
                testCase = "VAR operator - boolean default",
                expression = clientLogic {
                    registryKey("key1", default = false)
                },
                expected = """{"var":["key1", false]}"""
            ),
            JsonLogicTestData(
                testCase = "VAR operator - number default",
                expression = clientLogic {
                    registryKey("key1", default = 2.2)
                },
                expected = """{"var":["key1", 2.2]}"""
            ),
            JsonLogicTestData(
                testCase = "VAR operator - number default",
                expression = clientLogic {
                    buildListOfElements {
                        add(1)
                        add(registryKey("A"))
                        add(registryKey("B", default = "TEST"))
                    }
                },
                expected = """[1, {"var": "A"},{"var": ["B", "TEST"]} ]"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
