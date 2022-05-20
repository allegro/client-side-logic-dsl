package pl.allegro.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientLogicOptIn
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class AndOperationTest {

    @ParameterizedTest(name = "[{index}] AND operator -  {0}")
    @MethodSource("testData")
    fun `should map And operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "two keys",
                expression = clientLogic {
                    registryKey("key1").and(registryKey("key2"))
                },
                expected = """{ "and" : [{"var":"key1"}, {"var":"key2"}]}"""
            ),
            JsonLogicTestData(
                testCase = "chained",
                expression = clientLogic {
                    registryKey("key1")
                        .and(registryKey("key2"))
                        .and(registryKey("key3"))
                },
                expected = """{"and":[{"and":[{"var":"key1"},{"var":"key2"}]},{"var":"key3"}]}"""
            ),
            JsonLogicTestData(
                testCase = "find first falsy element",
                expression = clientLogic {
                    firstFalsyElementOrLastElement(registryKey("key1"), registryKey("key2"), registryKey("key3"))
                },
                expected = """{ "and" : [{"var":"key1"}, {"var":"key2"}, {"var":"key3"}]}"""
            ),
            JsonLogicTestData(
                testCase = "find first falsy element - keys and expression",
                expression = clientLogic {
                    firstFalsyElementOrLastElement(
                        registryKey("key1"),
                        registryKey("key2"),
                        registryKey("key3"),
                        2.plus(registryKey("test"))
                    )
                },
                expected = """{"and":[{"var":"key1"},{"var":"key2"},{"var":"key3"},{"+":[2,{"var":"test"}]}]}"""
            ),
            JsonLogicTestData(
                testCase = "find first falsy element - array",
                expression = clientLogic {
                    val elements = listOfElements(
                        registryKey("key1"),
                        registryKey("key2"),
                        registryKey("key3"),
                        2.plus(registryKey("test"))
                    )
                    firstFalsyElementOrLastElement(elements)
                },
                expected = """{"and":[{"var":"key1"},{"var":"key2"},{"var":"key3"},{"+":[2,{"var":"test"}]}]}"""
            ),
            JsonLogicTestData(
                testCase = "OR and nested AND operators",
                expression = clientLogic {
                    registryKey("key1")
                        .and(registryKey("key2"))
                        .or(registryKey("key3"))
                },
                expected = """{ "or" : [{ "and" : [{"var":"key1"}, {"var":"key2"}]}, {"var":"key3"}]}"""
            ),
            JsonLogicTestData(
                testCase = "check last truthy element is notEqual",
                expression = clientLogic {
                    registryKey("A")
                        .and(registryKey("B"))
                        .isNotEqual("")
                },
                expected = """{"!=":[{"and":[{"var":"A"},{"var":"B"}]},""]}"""
            ),
            JsonLogicTestData(
                testCase = "check last truthy element is notEqual - parenthesis",
                expression = clientLogic {
                    (registryKey("A").and(registryKey("B")))
                        .isNotEqual("")
                },
                expected = """{"!=":[{"and":[{"var":"A"},{"var":"B"}]},""]}"""
            ),
            JsonLogicTestData(
                testCase = "check with notEqual result",
                expression = clientLogic {
                    registryKey("A").and(
                        registryKey("B").isNotEqual("")
                    )
                },
                expected = """{"and":[{"var":"A"}, {"!=":[{"var":"B"},""]}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
