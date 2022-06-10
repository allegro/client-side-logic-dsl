package pl.allegro.mobile.logic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.JsonLogicTestData
import pl.allegro.mobile.logic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.mobile.logic.operators.toJsonLogicTestArgumentsStream

class MapOperationTest {

    @ParameterizedTest(name = "[{index}] MAP operator - {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "with multiply",
                expression = clientLogic {
                    buildListOfElements {
                        add(1)
                        add(2)
                        add(registryKey("test"))
                    }.map { it.multiply(2) }
                },
                expected = """{"map":[[1,2,{"var":"test"}],{"*":[{"var":""},2]}]}"""
            ),
            JsonLogicTestData(
                testCase = "map, filter, map",
                expression = clientLogic {
                    buildListOfElements {
                        add(1)
                        add(2)
                    }
                        .map { it.multiply(3) }
                        .filter { it.modulo(2) }
                        .map { concat("element: %s", it) }
                },
                expected = """{"map":[
                        |{"filter":[{"map":[[1,2],{"*":[{"var":""},3]}]},{"%":[{"var":""},2]}]},
                        |{"cat":["element:",{"var":""}]}]}""".trimMargin()
            ),
            JsonLogicTestData(
                testCase = "map, reduce (checkboxes)",
                expression = clientLogic {
                    listOfElements(registryKey("checkbox0"), registryKey("checkbox1"), registryKey("checkbox2"))
                        .map { If(it.isEqual("checked")) { 10 }.Else { 0 } }
                        .reduce { curr, acc -> curr.plus(acc) }
                },
                expected = """{"reduce":[
                        {"map":[
                        [{"var":"checkbox0"},{"var":"checkbox1"},{"var":"checkbox2"}],
                        {"if":[{"==":[{"var":""},"checked"]},10,0]}]}, 
                        {"+":[{"var":"current"},{"var":"accumulator"}]},0]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
