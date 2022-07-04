package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface IsBlankOperation {
    /**
     * Check if this string is empty or consists solely of whitespace characters.
     * @receiver Character sequence or client side operation that returns string
     * @return isBlank operator, evaluated client side.
     * Returns true if this string is empty or consists solely of whitespace characters.
     * @see: IsBlankOperationTest
     */
    @ClientLogicMarker
    fun ClientLogicElement.isBlank() = IsBlankOperatorFactory().create(this)
}

private class IsBlankOperatorFactory : OperatorFactory("isBlank")
