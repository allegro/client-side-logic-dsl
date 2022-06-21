package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface CapitalizeOperation {
    /**
     * Converts first character of this string to titlecased.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence client side data or operation results which will be injected to string
     * @return capitalize operator, evaluated client side.
     * Operator returns this string having its first letter titlecased.
     * @see: CapitalizeOperationTest
     */
    @ClientLogicMarker
    fun capitalize(sequence: ClientLogicElement) = CapitalizeOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun ClientLogicElement.capitalized() = CapitalizeOperatorFactory().create(this)
}

private class CapitalizeOperatorFactory : OperatorFactory("capitalize")
