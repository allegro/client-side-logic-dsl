package pl.allegro.logic.clientLogic.operators.string

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.StringElement

internal interface ContainsStringOperation {

    @ClientLogicMarker
    fun ClientLogicElement.containsString(subElement: ClientLogicElement) = contains(this, subElement)

    @ClientLogicMarker
    fun ClientLogicElement.containsString(subElement: String) = contains(this, StringElement(subElement))

    @ClientLogicMarker
    fun String.containsString(subElement: ClientLogicElement) = contains(StringElement(this), subElement)

    private fun contains(element: ClientLogicElement, subElement: ClientLogicElement) =
        InStringOperatorFactory().create(subElement, element)// different order on purpose!
}

private class InStringOperatorFactory : OperatorFactory("in")
