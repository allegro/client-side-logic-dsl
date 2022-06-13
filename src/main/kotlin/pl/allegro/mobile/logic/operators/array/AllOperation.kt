package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker

internal interface AllOperation {
    /**
     * Checks if all elements in the array fulfill given condition
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param verificationBlock client side logic operation that validates each element (e.g. { it.isGreaterThan(2) })
     * @return [all operator](https://jsonlogic.com/operations.html#all-none-and-some) evaluated client side.
     * Operator returns true if all elements fulfill condition and false otherwise
     * @see: AllOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.all(verificationBlock: ArrayTransformation) =
        AllOperatorFactory().create(element = this, verificationBlock)
}

private class AllOperatorFactory : ArrayOperatorFactory("all")
