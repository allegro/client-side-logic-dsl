package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface LengthOperation {
    /**
     * Returns the length of this character sequence.
     * @receiver String or client side operation that returns integer
     * @param sequence client side list or operation returning array
     * @return length of the string evaluated client side.
     * Operator returns integer size
     * @see: SizeOperationTest
     */
    @ClientLogicMarker
    fun lengthOf(sequence: ClientLogicElement) = LengthOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun ClientLogicElement.length() = LengthOperatorFactory().create(this)
}

private class LengthOperatorFactory : OperatorFactory("length")
