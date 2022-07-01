package pl.allegro.mobile.logic.operators.miscelanous

import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface CurrentTimeOperation {
    @ClientLogicMarker
    fun currentTimeInMillis() = CurrentTimeOperatorFactory().create()
}

private class CurrentTimeOperatorFactory : OperatorFactory("currentTime")
