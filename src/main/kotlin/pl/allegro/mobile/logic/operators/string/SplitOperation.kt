package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogic.toListOfElements
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface SplitOperation {
    /**
     * Splits string to a list of strings around occurrences of the specified delimiters.
     * @receiver Character sequence or client side operation that returns string
     * @param delimiters One or more characters to be used as delimiters
     * @return split operator, evaluated client side.
     * Returns list of strings.
     * @see: SplitOperationTest
     */
    @ClientLogicMarker
    fun ClientLogicElement.split(vararg delimiters: String) =
        SplitOperatorFactory().create(this, delimiters.toList())
}

private class SplitOperatorFactory {
    fun create(
        element: ClientLogicElement,
        delimiters: List<String>,
    ) = ClientLogicOperator.Builder("split")
        .add(element)
        .add(delimiters.distinct().map { StringElement(it) }.toListOfElements())
        .build()
}
