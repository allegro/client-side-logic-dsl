package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ListOfClientElements
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface OrOperation {

    @ClientLogicMarker
    fun ClientLogicElement.or(other: ClientLogicElement) =
        OrOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun firstTruthyElementOrLastElement(
        element: ClientLogicElement,
        vararg elements: ClientLogicElement
    ) = OrOperatorFactory().create(element, *elements)

    @ClientLogicMarker
    fun firstTruthyElementOrLastElement(elements: ListOfClientElements<in ClientLogicElement>) =
        OrOperatorFactory().create(elements)
}

private class OrOperatorFactory : OperatorFactory("or")
