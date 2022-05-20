package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface DoubleNotOperation {
    @ClientLogicMarker
    fun ClientLogicElement.castToBoolean() = DoubleNotOperatorFactory().create(this)
}

internal class DoubleNotOperatorFactory : OperatorFactory("!!")
