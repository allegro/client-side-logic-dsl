package pl.allegro.logic.clientLogic.operators.array

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ClientLogicOperator
import pl.allegro.logic.clientLogic.ClientLogicArray

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
