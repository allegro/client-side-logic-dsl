package pl.allegro.logic.clientLogic.operators.misc

import org.junit.jupiter.api.Test
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.clientLogic
import pl.allegro.logic.clientLogic.operators.isEqualAfterSerialization

@OptIn(ClientLogicOptIn::class)
class LogOperatorTest {

    @Test
    fun `LOG operator`() {
        val expression = clientLogic {
            log(registryKey("key1").and(registryKey("key2")))
        }

        expression.isEqualAfterSerialization("{\"log\":{\"and\":[{\"var\":\"key1\"},{\"var\":\"key2\"}]}}")
    }

    @Test
    fun `LOG operator (inside expression)`() {
        val expression = clientLogic {
            If(log(registryKey("temp")).isLessThan(0)) {
                "freezing"
            }.Else {
                "liquid_or_gas"
            }
        }

        expression.isEqualAfterSerialization(
            "{\"if\" : [\n" +
                "{\"<\": [{\"log\":{\"var\":\"temp\"}}, 0] }, \"freezing\",\n" +
                "\"liquid_or_gas\"\n" +
                "]}"
        )
    }
}
