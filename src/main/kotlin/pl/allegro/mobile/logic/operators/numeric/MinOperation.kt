package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ListOfClientElements
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface MinOperation {
    @ClientLogicMarker
    fun ListOfClientElements<ClientLogicElement>.min() = MinOperatorFactory().create(this)
}

private class MinOperatorFactory : OperatorFactory("min")
