package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface SizeOperation {
    /**
     * Returns the size of the array.
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param array client side list or operation returning array
     * @return size of the array evaluated client side.
     * Operator returns integer size
     * @see: SizeOperationTest
     */
    @ClientLogicMarker
    fun sizeOf(array: ClientLogicArray<in ClientLogicElement>) = SizeOperatorFactory().create(array)

    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.size() = SizeOperatorFactory().create(this)
}

private class SizeOperatorFactory : OperatorFactory("size")
