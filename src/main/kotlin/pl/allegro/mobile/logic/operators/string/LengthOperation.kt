package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface LengthOperation {
    /**
     * Returns the length of this character sequence.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence character sequence or operation returning string
     * @return length of the string evaluated client side.
     * Operator returns integer size
     * @see: LengthOperationTest
     */
    @ClientLogicMarker
    fun lengthOf(sequence: ClientLogicElement) = LengthOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun ClientLogicElement.length() = LengthOperatorFactory().create(this)
}

private class LengthOperatorFactory : OperatorFactory("length")
