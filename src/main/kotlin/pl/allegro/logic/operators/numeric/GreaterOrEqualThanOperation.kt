package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface GreaterOrEqualThanOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isGreaterOrEqual(other: ClientLogicElement) = GreaterOrEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isGreaterOrEqual(other: Number) = isGreaterOrEqual(NumberElement(other))

    @ClientLogicMarker
    fun Number.isGreaterOrEqual(other: ClientLogicElement) = NumberElement(this).isGreaterOrEqual(other)
}

private class GreaterOrEqualOperatorFactory : OperatorFactory(">=")
