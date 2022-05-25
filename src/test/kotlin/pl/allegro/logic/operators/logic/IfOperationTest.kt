package pl.allegro.logic.operators.logic

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.ClientRegistryDataElement
import pl.allegro.logic.clientLogic
import pl.allegro.logic.operators.JsonLogicTestData
import pl.allegro.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.operators.toJsonLogicTestArgumentsStream

class IfOperationTest {

    @ParameterizedTest(name = "[{index}] IF operator - {0}")
    @MethodSource("testData")
    fun `should map if operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "if else",
                expression = clientLogic {
                    If(registryKey("key1")) {
                        "YES"
                    }.Else {
                        "nope"
                    }
                },
                expected = """{"if" : [{"var":"key1"},"YES","nope"]}"""
            ),
            JsonLogicTestData(
                testCase = "multiple conditions - else if",
                expression = clientLogic {
                    val temp = registryKey("temp")
                    If(temp.isLessThan(0)) {
                        "freezing"
                    }.ElseIf(temp.isLessThan(100)) {
                        "liquid"
                    }.Else {
                        "gas"
                    }
                },
                expected = """{"if" : [{"<": [{"var":"temp"}, 0] }, "freezing",{"<": [{"var":"temp"}, 100] }, "liquid","gas"]}"""
            ),
            JsonLogicTestData(
                testCase = "check is null or empty (isFalsy)",
                expression = clientLogic {
                    registryKey("test").isEmpty()
                },
                expected = """{"if":[{"var":"test"},false,true]}"""
            ),
            JsonLogicTestData(
                testCase = "check is not null or empty (isTruthy)",
                expression = clientLogic {
                    registryKey("test").isNotEmpty()
                },
                expected = """{"if":[{"var":"test"},true,false]}"""
            ),
            JsonLogicTestData(
                testCase = "nested if",
                expression = clientLogic {
                    val keyValueCheck = If(registryKey("key1")) { 0 }.Else { 66 }
                    buildListOfElements {
                        add(keyValueCheck)
                        add(1)
                        add(2)
                        add(3)
                    }.max()
                },
                expected = """{"max":[{"if":[{"var":"key1"},0,66]},1,2,3]}"""
            ),
            JsonLogicTestData(
                testCase = "sum if operation result and number`",
                expression = clientLogic {
                    fun ClientRegistryDataElement.checkedToInt() = If(isEqual("checked")) { 1 }.Else { 0 }
                    registryKey("key1").checkedToInt().plus(3)
                },
                expected = """{"+":[{"if":[{"==":[{"var":"key1"},"checked"]},1,0]},3]}"""
            )
        ).toJsonLogicTestArgumentsStream()
    }
}
