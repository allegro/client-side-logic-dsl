package pl.allegro.logic.clientLogic.operators.array

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class SomeOperationTest {

    @ParameterizedTest(name = "[{index}] SOME operator - {0}")
    @MethodSource("testData")
    fun `should map array operations to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "some equal true",
                expression = clientLogic {
                    listOfElements(registryKey("flag0"), registryKey("flag2"))
                        .some { it.isEqual(true) }
                },
                expected = """{"some":[[{"var":"flag0"},{"var":"flag2"}],{"==":[{"var":""},true]}]}"""
            ),
            JsonLogicTestData(
                testCase = "map, some",
                expression = clientLogic {
                    listOfElements(registryKey("flag0"), registryKey("flag2"))
                        .map { concat("%stest", it) }
                        .some { it.isEqual("my_test") }
                },
                expected = """{"some":[
                        {"map":[[{"var":"flag0"},{"var":"flag2"}],{"cat":[{"var":""},"test"]}]},
                        {"==":[{"var":""},"my_test"]}]}"""
            ),
        ).toJsonLogicTestArgumentsStream()
    }
}
