package pl.allegro.logic.clientLogic.operators.demo

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.ClientRegistryDataElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class CheckboxesOperatorsTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    fun `should map checkbox examples to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "CHECKBOX_GROUP example",
                expression = clientLogic {
                    fun String.isChecked() = registryKey(this).isEqual(CHECKED)
                    If(CHECKBOX_0.isChecked().and(CHECKBOX_1.isChecked()).and(CHECKBOX_2.isChecked())) {
                        CHECKED
                    }.ElseIf(CHECKBOX_0.isChecked().or(CHECKBOX_1.isChecked()).or(CHECKBOX_2.isChecked())) {
                        INDETERMINATE
                    }.Else {
                        UNCHECKED
                    }
                },
                expected = """{"if":[
                {"and":[{"and":[{"==":[{"var":"checkbox0"},"checked"]},{"==":[{"var":"checkbox1"},"checked"]}]},{"==":[{"var":"checkbox2"},"checked"]}]},"checked",
                {"or":[{"or":[{"==":[{"var":"checkbox0"},"checked"]},{"==":[{"var":"checkbox1"},"checked"]}]},{"==":[{"var":"checkbox2"},"checked"]}]},"indeterminate",
                "unchecked"]}"""
            ),
            JsonLogicTestData(
                testCase = "CHECKBOX_GROUP example - all, none",
                expression = clientLogic {
                    fun ClientRegistryDataElement.isChecked() = this.isEqual(CHECKED)
                    val allCheckboxes =
                        listOfElements(registryKey(CHECKBOX_0), registryKey(CHECKBOX_1), registryKey(CHECKBOX_2))
                    If(allCheckboxes.all { it.isChecked() }) {
                        CHECKED
                    }.ElseIf(allCheckboxes.none { it.isChecked() }) {
                        INDETERMINATE
                    }.Else {
                        UNCHECKED
                    }
                },
                expected = """{"if":[
                {"all":[[{"var":"checkbox0"},{"var":"checkbox1"},{"var":"checkbox2"}],{"==":[{"var":""},"checked"]}]},"checked",
                {"none":[[{"var":"checkbox0"},{"var":"checkbox1"},{"var":"checkbox2"}],{"==":[{"var":""},"checked"]}]},"indeterminate",
                "unchecked"]}"""
            ),
            JsonLogicTestData(
                testCase = "SELECTED_COUNT example",
                expression = clientLogic {
                    fun String.checkedToInt() = If(registryKey(this).isEqual(CHECKED)) { 1 }.Else { 0 }
                    CHECKBOX_0.checkedToInt()
                        .plus(CHECKBOX_1.checkedToInt())
                        .plus(CHECKBOX_2.checkedToInt())
                },
                expected = """{"+":[
                {"if":[{"==":[{"var":"checkbox0"},"checked"]},1,0]},
                {"if":[{"==":[{"var":"checkbox1"},"checked"]},1,0]},
                {"if":[{"==":[{"var":"checkbox2"},"checked"]},1,0]}
                ]}"""
            ),
            JsonLogicTestData(
                testCase = "SELECTED_COUNT example - map, reduce",
                expression = clientLogic {
                    listOf(CHECKBOX_0, CHECKBOX_1, CHECKBOX_2)
                        .map { registryKey(it) }
                        .toListOfElements()
                        .map { If(it.isEqual(CHECKED)) { 1 }.Else { 0 } }
                        .reduce { current, accumulator -> current.plus(accumulator) }
                },
                expected = """
                {"reduce":[
                    {"map":[[
                    {"var":"checkbox0"},{"var":"checkbox1"},{"var":"checkbox2"}],
                    {"if":[{"==":[{"var":""},"checked"]},1,0]}]},
                {"+":[{"var":"current"},{"var":"accumulator"}]},
                0]}"""
            ),
        ).toJsonLogicTestArgumentsStream()

        private const val CHECKBOX_0 = "checkbox0"
        private const val CHECKBOX_1 = "checkbox1"
        private const val CHECKBOX_2 = "checkbox2"

        private const val CHECKED = "checked"
        private const val UNCHECKED = "unchecked"
        private const val INDETERMINATE = "indeterminate"
    }
}
