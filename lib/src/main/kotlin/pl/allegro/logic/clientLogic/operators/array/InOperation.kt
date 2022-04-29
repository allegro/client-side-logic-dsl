package pl.allegro.logic.clientLogic.operators.array

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.ClientLogicArray

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
