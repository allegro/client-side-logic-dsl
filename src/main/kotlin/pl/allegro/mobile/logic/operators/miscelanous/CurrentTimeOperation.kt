package pl.allegro.mobile.logic.operators.miscelanous

import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface CurrentTimeOperation {
    /**
     * The difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC.
     * @return currentTime operator, evaluated client side.
     * Operator returns difference, measured in milliseconds, between current time and midnight January 1, 1970 UTC.
     * @see: CurrentTimeOperationTest
     */
    @ClientLogicMarker
    fun currentTimeInMillis() = CurrentTimeOperatorFactory().create()
}

private class CurrentTimeOperatorFactory : OperatorFactory("currentTime")
