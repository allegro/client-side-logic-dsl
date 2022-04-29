package pl.allegro.logic.clientLogic.operators.array

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.ClientLogicArray

internal interface FilterOperation {
    /**
     * Filter out elements that does not fulfill condition
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param verificationBlock client side logic operation that validates each element (e.g. { it.isGreaterThan(2) })
     * @return [filter operator](https://jsonlogic.com/operations.html#map-reduce-and-filter), evaluated client side.
     * Operator returns list of elements that fulfill given condition
     * @see: FilterOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.filter(verificationBlock: ArrayTransformation) =
        FilterOperatorFactory().create(element = this, verificationBlock)
}

private class FilterOperatorFactory : ArrayOperatorFactory("filter")
