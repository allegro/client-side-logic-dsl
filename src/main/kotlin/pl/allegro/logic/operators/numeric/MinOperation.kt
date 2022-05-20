package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ListOfClientElements
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface MinOperation {
    @ClientLogicMarker
    fun ListOfClientElements<ClientLogicElement>.min() = MinOperatorFactory().create(this)
}

private class MinOperatorFactory : OperatorFactory("min")
