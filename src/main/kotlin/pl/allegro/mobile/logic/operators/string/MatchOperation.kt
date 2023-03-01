package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface MatchOperation {
    /**
     * Check if string match to given regex
     * @receiver Character sequence or client side operation that returns string
     * @param regex regex which we want to use
     * @return true/false, depending on if string match to given regex
     * Returns true or false depending on if string match to given regex
     * @see: MatchOperationTest
     */
    @ClientLogicMarker
    fun ClientLogicElement.match(regex: String) =
        MatchOperatorFactory().create(this, regex)
}

private class MatchOperatorFactory {
    fun create(
        element: ClientLogicElement,
        regex: String
    ) = ClientLogicOperator.Builder("match")
        .add(element)
        .add(StringElement(regex))
        .build()
}

