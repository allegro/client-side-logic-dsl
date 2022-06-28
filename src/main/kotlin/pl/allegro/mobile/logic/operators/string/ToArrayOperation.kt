package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface ToArrayOperation {
    /**
     * Splits this char sequence to a list of strings.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence client side data or operation results which will be injected to string
     * @return toArray operator, evaluated client side.
     * Operator returns this string split into singular characters list.
     * @see: ToArrayOperationTest
     */
    @ClientLogicMarker
    fun asArray(sequence: ClientLogicElement) = ToArrayOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun ClientLogicElement.toArray() = ToArrayOperatorFactory().create(this)
}

private class ToArrayOperatorFactory : OperatorFactory("toArray")
