package pl.allegro.mobile.logic.operators

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface DropOperation {
    /**
     * Drops n elements in characters sequence or client side elements list.
     * @receiver Character sequence, list of client side elements or client side operation that returns string or list.
     * @param count number of elements to drop
     * @param mode indicates from which end elements should be dropped
     * @return drop operator, evaluated client side.
     * Operator returns a list or character sequence containing all elements except first/last n elements.
     * @see: DropOperationTest
     */

    @ClientLogicMarker
    fun ClientLogicElement.drop(count: Int, mode: DropMode = DropMode.First) = DropOperatorFactory().create(this, count, mode)
}

private class DropOperatorFactory {
    fun create(
        element: ClientLogicElement,
        count: Int,
        mode: DropMode
    ) = ClientLogicOperator.Builder("drop")
        .add(element)
        .add(StringElement(count.toString()))
        .add(mode.toStringElement())
        .build()

    private fun DropMode.toStringElement() = StringElement(
        when (this) {
            is DropMode.First -> "first"
            is DropMode.Last  -> "last"
        }
    )
}

sealed class DropMode {
    object First : DropMode()
    object Last : DropMode()
}