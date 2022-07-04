package pl.allegro.mobile.logic.operators

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker

internal interface ReverseOperation {
    /**
     * Reverses characters sequence or elements order in the list.
     * @receiver Character sequence, list of client side elements or client side operation that returns string or list.
     * @param element string, client side list or operation returning array or character sequence
     * @return reverse operator, evaluated client side.
     * Operator returns this string or list of client side elements reversed.
     * @see: ReverseOperationTest
     */
    @ClientLogicMarker
    fun reverse(element: ClientLogicElement) = ReverseOperatorFactory().create(element)

    @ClientLogicMarker
    fun ClientLogicElement.reversed() = ReverseOperatorFactory().create(this)
}

private class ReverseOperatorFactory : OperatorFactory("reverse")
