package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface DoubleNotOperation {
    @ClientLogicMarker
    fun ClientLogicElement.castToBoolean() = DoubleNotOperatorFactory().create(this)
}

internal class DoubleNotOperatorFactory : OperatorFactory("!!")
