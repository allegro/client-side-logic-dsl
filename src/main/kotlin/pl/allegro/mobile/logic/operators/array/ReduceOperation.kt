package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.BooleanElement
import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.ClientRegistryDataElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.RegistryDataElement
import pl.allegro.mobile.logic.StringElement

internal interface ReduceOperation {

    /**
     * Reduce elements of the array into one element
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param initialValue first element
     * @param transformation function that takes current accumulator value and an element,
     * and calculates the next accumulator value (e.g. current.plus(accumulator))
     * @return [reduce operator](https://jsonlogic.com/operations.html#map-reduce-and-filter), evaluated client side.
     * @see: ReduceOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.reduce(
        initialValue: ClientLogicElement,
        transformation: ReduceTransformation
    ) = reduceInternal(this, initialValue, transformation)

    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.reduce(
        initialValue: Number,
        transformation: ReduceTransformation
    ) = reduceInternal(this, NumberElement(initialValue), transformation)

    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.reduce(
        initialValue: Boolean,
        transformation: ReduceTransformation
    ) = reduceInternal(this, BooleanElement(initialValue), transformation)

    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.reduce(
        initialValue: String,
        transformation: ReduceTransformation
    ) = reduceInternal(this, StringElement(initialValue), transformation)

    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.reduce(transformation: ReduceTransformation) =
        reduceInternal(this, NumberElement(0), transformation)

    private fun <T : ClientLogicElement> reduceInternal(
        element: ClientLogicArray<T>,
        initialValue: ClientLogicElement,
        transformation: ReduceTransformation
    ) = ReduceOperatorFactory().create(element, transformation, initialValue)
}

private class ReduceOperatorFactory {

    fun <T : ClientLogicElement> create(
        element: ClientLogicArray<T>,
        transformation: ReduceTransformation,
        initialValue: ClientLogicElement
    ) = createOperator(element, transformation, initialValue)

    private fun createOperator(
        element: ClientLogicElement,
        transformation: ReduceTransformation,
        initialValue: ClientLogicElement
    ) = ClientLogicOperator(
        "reduce",
        element,
        transformation(RegistryDataElement("current"), RegistryDataElement("accumulator")),
        initialValue
    )
}

typealias ReduceTransformation = (current: ClientRegistryDataElement, accumulator: ClientRegistryDataElement) -> ClientLogicElement
