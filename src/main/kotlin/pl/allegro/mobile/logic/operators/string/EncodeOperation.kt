package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface EncodeOperation {
    /**
     * Returns the encoded value of this character sequence.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence character sequence or operation returning string
     * @return encoded value of the string evaluated client side.
     * Operator returns encoded value
     * @see: EncodeOperationTest
     */
    @ClientLogicMarker
    fun encodeOf(sequence: ClientLogicElement) = EncodeOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun ClientLogicElement.encode() = EncodeOperatorFactory().create(this)
}

private class EncodeOperatorFactory : OperatorFactory("encode")
