package pl.allegro.logic.clientLogic.operators.demo

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.JsonLogicTestData
import pl.allegro.logic.clientLogic.operators.assertSerializedExpressionMatchesExpected
import pl.allegro.logic.clientLogic.operators.toJsonLogicTestArgumentsStream

@OptIn(ClientLogicOptIn::class)
class TileCalculatorOperatorsTest {

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testData")
    fun `should map tile calculation examples to json`(testCaseName: String, jsonLogicTestData: JsonLogicTestData) {
        jsonLogicTestData.assertSerializedExpressionMatchesExpected()
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            JsonLogicTestData(
                testCase = "FLOOR_AREA example",
                expression = clientLogic {
                    registryKey(FLOOR_WIDTH).multiply(registryKey(FLOOR_LENGTH))
                },
                expected = """{"*":[{"var":"floorWidth"},{"var":"floorLength"}]}"""
            ),
            JsonLogicTestData(
                testCase = "NUM OF TILES example",
                expression = clientLogic {
                    val tileAreaCm2 = registryKey(TILE_WIDTH).multiply(registryKey(TILE_LENGTH))
                    val tileAreaM2 = tileAreaCm2.divide(10_000)
                    registryKey(FLOOR_AREA).divide(tileAreaM2)
                },
                expected = """{"/":[{"var":"floorArea"},{"/":[{"*":[{"var":"tileWidth"},{"var":"tileLength"}]},10000]}]}"""
            ),
            JsonLogicTestData(
                testCase = "NUM OF TILES example (validated)",
                expression = clientLogic {
                    val tileAreaM2 = registryKey(TILE_WIDTH).multiply(registryKey(TILE_LENGTH)).divide(10_000)
                    If(tileAreaM2.isGreaterThan(0)) {
                        registryKey(FLOOR_AREA).divide(tileAreaM2)
                    }.Else {
                        "invalid tile dimen"
                    }
                },
                expected = """{"if":[
            |{">":[{"/":[{"*":[{"var":"tileWidth"},{"var":"tileLength"}]},10000]},0]},
            |{"/":[{"var":"floorArea"},{"/":[{"*":[{"var":"tileWidth"},{"var":"tileLength"}]},10000]}]},
            |"invalidtiledimen"]}""".trimMargin()
            ),
            JsonLogicTestData(
                testCase = "NUMBER_OF_TILES_WITH_MARGIN example",
                expression = clientLogic {
                    registryKey(NUMBER_OF_TILES).multiply(1.1)
                },
                expected = """{"*":[{"var":"numOfTiles"},1.1]}"""
            ),
        ).toJsonLogicTestArgumentsStream()

        private const val FLOOR_WIDTH = "floorWidth"
        private const val FLOOR_LENGTH = "floorLength"

        private const val TILE_WIDTH = "tileWidth"
        private const val TILE_LENGTH = "tileLength"

        private const val FLOOR_AREA = "floorArea"
        private const val NUMBER_OF_TILES = "numOfTiles"
    }
}
