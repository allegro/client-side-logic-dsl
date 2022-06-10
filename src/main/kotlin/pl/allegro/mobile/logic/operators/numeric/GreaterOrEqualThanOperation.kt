package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface GreaterOrEqualThanOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isGreaterOrEqual(other: ClientLogicElement) = GreaterOrEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isGreaterOrEqual(other: Number) = isGreaterOrEqual(NumberElement(other))

    @ClientLogicMarker
    fun Number.isGreaterOrEqual(other: ClientLogicElement) = NumberElement(this).isGreaterOrEqual(other)
}

private class GreaterOrEqualOperatorFactory : OperatorFactory(">=")
