package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface DoubleNotOperation {
    @ClientLogicMarker
    fun ClientLogicElement.castToBoolean() = DoubleNotOperatorFactory().create(this)
}

internal class DoubleNotOperatorFactory : OperatorFactory("!!")
