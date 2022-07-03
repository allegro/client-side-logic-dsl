package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface DistinctOperation {
    /**
     * Distinct elements from the given array.
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @return distinct operator evaluated client side.
     * Returns a list containing only distinct elements from the given list.
     * @see: DistinctOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.distinct() = DistinctOperatorFactory().create(this)
}

private class DistinctOperatorFactory : ArrayOperatorFactory("distinct") {
    fun create(vararg elements: ClientLogicElement) = ClientLogicOperator(name, *elements)
}
