package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ListOfClientElements
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface MaxOperation {
    @ClientLogicMarker
    fun ListOfClientElements<ClientLogicElement>.max() = MaxOperatorFactory().create(this)
}

private class MaxOperatorFactory : OperatorFactory("max")
