package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.ListOfClientElements
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface MinOperation {
    @ClientLogicMarker
    fun ListOfClientElements<ClientLogicElement>.min() = MinOperatorFactory().create(this)
}

private class MinOperatorFactory : OperatorFactory("min")
