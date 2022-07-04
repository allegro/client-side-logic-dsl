package pl.allegro.mobile.logic.operators.misc

import org.junit.jupiter.api.Test
import pl.allegro.mobile.logic.clientLogic
import pl.allegro.mobile.logic.operators.isEqualAfterSerialization

class CurrentTimeOperatorTest {

    @Test
    fun `CurrentTime operator`() {
        val expression = clientLogic { currentTimeInMillis() }

        expression.isEqualAfterSerialization("{\"currentTime\":[]}")
    }

    @Test
    fun `CurrentTime operator (inside expression)`() {
        val expression = clientLogic {
            currentTimeInMillis().betweenOrEqual(min = registryKey("startTime"), max = registryKey("endTime"))
        }

        expression.isEqualAfterSerialization("{\"<=\":[{\"var\":\"startTime\"},{\"currentTime\":[]},{\"var\":\"endTime\"}]}")
    }
}
