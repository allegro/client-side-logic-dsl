package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface SortOperation {
    /**
     * Sorts the array according to the order mode.
     * @receiver Array of client side elements or client side operation that returns array.
     * @param mode indicates order of sorting
     * @return sort operator, evaluated client side.
     * Operator returns a sorted array according to the given mode.
     * @see: SortOperationTest
     */

    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.sort(mode: SortMode = SortMode.Ascending) =
        SortOperatorFactory().create(this, mode)
}

private class SortOperatorFactory {
    fun create(
        element: ClientLogicElement,
        mode: SortMode
    ) = ClientLogicOperator.Builder("sort")
        .add(element)
        .add(mode.toStringElement())
        .build()

    private fun SortMode.toStringElement() = StringElement(
        when (this) {
            is SortMode.Ascending  -> "asc"
            is SortMode.Descending -> "desc"
        }
    )
}

sealed class SortMode {
    object Ascending : SortMode()
    object Descending : SortMode()
}
