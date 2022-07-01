package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker

internal interface FindOperation {
    /**
     * Finds the first element matching the given predicate.
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param verificationBlock client side logic operation that validates each element (e.g. { it.isGreaterThan(2) })
     * @return find operator evaluated client side.
     * Operator returns the first element matching the given predicate, or null result if no such element was found.
     * @see: FindOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.find(verificationBlock: ArrayTransformation) =
        FindOperatorFactory().create(element = this, verificationBlock)
}

private class FindOperatorFactory : ArrayOperatorFactory("find")
