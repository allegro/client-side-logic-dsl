package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface LowercaseOperation {
    /**
     * Converts the character sequence to lower case.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence client side data or operation results which will be injected to string
     * @return lowercase operator, evaluated client side.
     * Operator returns a copy of this string converted to lower case.
     * @see: LowercaseOperationTest
     */
    @ClientLogicMarker
    fun lowercase(sequence: ClientLogicElement) = LowercaseOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun ClientLogicElement.toLowercase() = LowercaseOperatorFactory().create(this)
}

private class LowercaseOperatorFactory : OperatorFactory("lowercase")