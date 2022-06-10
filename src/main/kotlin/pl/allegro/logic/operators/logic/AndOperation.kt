package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ListOfClientElements
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.FlattenableOperatorFactory
import pl.allegro.logic.operators.OperatorFactory

internal interface AndOperation {
    @ClientLogicMarker
    fun ClientLogicElement.and(other: ClientLogicElement) = AndOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun and(listOfElements: ListOfClientElements<in ClientLogicElement>) =
        AndOperatorFactory().create(listOfElements)
}

private class AndOperatorFactory : FlattenableOperatorFactory("and")
