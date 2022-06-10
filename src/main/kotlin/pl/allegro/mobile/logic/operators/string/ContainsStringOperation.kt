package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.StringElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

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
