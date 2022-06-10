package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ListOfClientElements
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.FlattenableOperatorFactory
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface OrOperation {

    @ClientLogicMarker
    fun ClientLogicElement.or(other: ClientLogicElement) =
        OrOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun or(elements: ListOfClientElements<in ClientLogicElement>) =
        OrOperatorFactory().create(elements)
}

private class OrOperatorFactory : FlattenableOperatorFactory("or")
