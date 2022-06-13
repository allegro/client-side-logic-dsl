package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker

internal interface SomeOperation {
    /**
     * Checks if some of elements in the array fulfill given condition
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param verificationBlock client side logic operation that validates each element (e.g. { it.isGreaterThan(2) })
     * @return [some operator](https://jsonlogic.com/operations.html#all-none-and-some), evaluated client side.
     * Operator returns true if some element fulfill condition and false otherwise
     * @see: SomeOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.some(verificationBlock: ArrayTransformation) =
        SomeOperatorFactory().create(element = this, verificationBlock)
}

private class SomeOperatorFactory : ArrayOperatorFactory("some")
