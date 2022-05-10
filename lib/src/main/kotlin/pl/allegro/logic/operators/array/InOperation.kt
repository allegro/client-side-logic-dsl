package pl.allegro.logic.operators.array

import pl.allegro.logic.ClientLogicArray
import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface InOperation {
    /**
     * Check if element is a member of the array
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param element element to find
     * @return [in operator](https://jsonlogic.com/operations.html#in), evaluated client side.
     * Operator returns true if element found and false otherwise
     * @see: InOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.contains(element: ClientLogicElement) =
        InOperatorFactory().create(element, this)
}

private class InOperatorFactory : OperatorFactory("in")
