package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker

internal interface MapOperation {
    /**
     * Map each element of the array
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param transformation operation to perform on eaach element (e.g. { it.plus(2) })
     * @return [map operator](https://jsonlogic.com/operations.html#map-reduce-and-filter), evaluated client side.
     * Operator returns list of transformed elements
     * @see: MapOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.map(transformation: ArrayTransformation) =
        MapOperatorFactory().create(element = this, transformation)
}

private class MapOperatorFactory : ArrayOperatorFactory("map")
