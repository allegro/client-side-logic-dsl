package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface GreaterThanOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isGreaterThan(other: ClientLogicElement) = GreaterThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isGreaterThan(other: Number) = isGreaterThan(NumberElement(other))

    @ClientLogicMarker
    fun Number.isGreaterThan(other: ClientLogicElement) = NumberElement(this).isGreaterThan(other)
}

private class GreaterThanOperatorFactory : OperatorFactory(">")
