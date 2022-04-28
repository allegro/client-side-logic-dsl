package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ListOfClientElements
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface AndOperation {
    @ClientLogicMarker
    fun ClientLogicElement.and(other: ClientLogicElement) = AndOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun firstFalsyElementOrLastElement(
        element: ClientLogicElement,
        vararg elements: ClientLogicElement
    ) = AndOperatorFactory().create(element, *elements)

    @ClientLogicMarker
    fun firstFalsyElementOrLastElement(listOfElements: ListOfClientElements<in ClientLogicElement>) =
        AndOperatorFactory().create(listOfElements)
}

private class AndOperatorFactory : OperatorFactory("and")
