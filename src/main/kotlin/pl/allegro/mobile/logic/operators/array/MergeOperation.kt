package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator

internal interface MergeOperation {
    /**
     * Merge two arrays into one
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param other another client side list or operation returning array
     * @return [merge operator](https://jsonlogic.com/operations.html#merge), evaluated client side.
     * Operator returns merged list
     * @see: MergeOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.mergeWith(other: ClientLogicArray<in ClientLogicElement>) =
        MergeOperatorFactory().create(this, other)
}

private class MergeOperatorFactory : ArrayOperatorFactory("merge") {
    fun create(vararg elements: ClientLogicElement) = ClientLogicOperator(name, *elements)
}
