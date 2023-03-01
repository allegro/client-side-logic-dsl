package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface MatchOperation {
    /**
     * Check if a string matches to given regex pattern
     * @receiver Character sequence or client side operation that returns string
     * @param regexPattern regex pattern which we want to use
     * @return match operator, evaluated client side.
     * Returns true or false depending on if the string matches to given regex pattern.
     * @see: MatchOperationTest
     */
    @ClientLogicMarker
    fun ClientLogicElement.match(regexPattern: String) =
        MatchOperatorFactory().create(this, regexPattern)
}

private class MatchOperatorFactory {
    fun create(
        element: ClientLogicElement,
        regexPattern: String
    ) = ClientLogicOperator.Builder("match")
        .add(element)
        .add(StringElement(regexPattern))
        .build()
}

