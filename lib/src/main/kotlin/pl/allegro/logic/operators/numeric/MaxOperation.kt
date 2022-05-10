package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ListOfClientElements
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface MaxOperation {
    @ClientLogicMarker
    fun ListOfClientElements<ClientLogicElement>.max() = MaxOperatorFactory().create(this)
}

private class MaxOperatorFactory : OperatorFactory("max")
