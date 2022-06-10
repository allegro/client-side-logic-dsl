package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ListOfClientElements
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface MaxOperation {
    @ClientLogicMarker
    fun ListOfClientElements<ClientLogicElement>.max() = MaxOperatorFactory().create(this)
}

private class MaxOperatorFactory : OperatorFactory("max")
