package pl.allegro.logic.clientLogic.operators.array

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ClientLogicArray

internal interface NoneOperation {
    /**
     * Checks if none of elements in the array fulfill given condition
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param verificationBlock client side logic operation that validates each element (e.g. { it.isGreaterThan(2) })
     * @return [none operator](https://jsonlogic.com/operations.html#all-none-and-some), evaluated client side.
     * Operator returns true if no element fulfill condition and false otherwise
     * @see: NoneOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.none(verificationBlock: ArrayTransformation) =
        NoneOperatorFactory().create(element = this, verificationBlock)
}

private class NoneOperatorFactory : ArrayOperatorFactory("none")
