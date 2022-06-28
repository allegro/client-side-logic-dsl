package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface ReplaceOperation {
    /**
     * Replaces a given string with the new one.
     * @receiver Character sequence or client side operation that returns string
     * @param oldString character sequence to be replaced
     * @param newString replaced string
     * @param count number of occurrences to replace
     * @return replace operator, evaluated client side.
     * Returns a new string with occurrences of oldString replaced with newString.
     * @see: ReplaceOperationTest
     */
    @ClientLogicMarker
    fun ClientLogicElement.replace(oldString: String, newString: String, count: ReplaceCount = ReplaceCount.All) =
        ReplaceOperatorFactory().create(this, oldString, newString, count)
}

private class ReplaceOperatorFactory {
    fun create(
        element: ClientLogicElement,
        oldString: String,
        newString: String,
        count: ReplaceCount
    ) = ClientLogicOperator.Builder("replace")
        .add(element)
        .add(StringElement(oldString))
        .add(StringElement(newString))
        .add(StringElement(count.toString()))
        .build()

    private fun ReplaceCount.toString() = when(this) {
        is ReplaceCount.All -> "all"
        is ReplaceCount.Exact -> count.toString()
    }
}

sealed class ReplaceCount {
    object All : ReplaceCount()
    data class Exact(val count: Int) : ReplaceCount()
}