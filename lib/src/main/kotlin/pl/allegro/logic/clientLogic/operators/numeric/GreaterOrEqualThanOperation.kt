package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.NumberElement

internal interface GreaterOrEqualThanOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isGreaterOrEqual(other: ClientLogicElement) = GreaterOrEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isGreaterOrEqual(other: Number) = isGreaterOrEqual(NumberElement(other))

    @ClientLogicMarker
    fun Number.isGreaterOrEqual(other: ClientLogicElement) = NumberElement(this).isGreaterOrEqual(other)
}

private class GreaterOrEqualOperatorFactory : OperatorFactory(">=")
