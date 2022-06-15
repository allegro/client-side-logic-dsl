package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface UppercaseOperation {
    /**
     * Converts the character sequence to upper case.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence client side data or operation results which will be injected to string
     * @return uppercase operator, evaluated client side.
     * Operator returns a copy of this string converted to upper case.
     * @see: UppercaseOperationTest
     */
    @ClientLogicMarker
    fun uppercase(sequence: ClientLogicElement) = UppercaseOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun ClientLogicElement.toUppercase() = UppercaseOperatorFactory().create(this)
}

private class UppercaseOperatorFactory : OperatorFactory("lowercase")