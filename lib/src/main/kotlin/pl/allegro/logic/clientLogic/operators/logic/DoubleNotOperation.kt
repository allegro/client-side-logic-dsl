package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface DoubleNotOperation {
    @ClientLogicMarker
    fun ClientLogicElement.castToBoolean() = DoubleNotOperatorFactory().create(this)
}

internal class DoubleNotOperatorFactory : OperatorFactory("!!")
