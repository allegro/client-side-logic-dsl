package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ListOfClientElements
import pl.allegro.mobile.logic.operators.FlattenableOperatorFactory

internal interface AndOperation {
    @ClientLogicMarker
    fun ClientLogicElement.and(other: ClientLogicElement) = AndOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun and(listOfElements: ListOfClientElements<in ClientLogicElement>) =
        AndOperatorFactory().create(listOfElements)
}

private class AndOperatorFactory : FlattenableOperatorFactory("and")
