package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ListOfClientElements
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.FlattenableOperatorFactory
import pl.allegro.logic.operators.OperatorFactory

internal interface OrOperation {

    @ClientLogicMarker
    fun ClientLogicElement.or(other: ClientLogicElement) =
        OrOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun or(elements: ListOfClientElements<in ClientLogicElement>) =
        OrOperatorFactory().create(elements)
}

private class OrOperatorFactory : FlattenableOperatorFactory("or")
