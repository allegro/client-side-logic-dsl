package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface GreaterThanOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isGreaterThan(other: ClientLogicElement) = GreaterThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isGreaterThan(other: Number) = isGreaterThan(NumberElement(other))

    @ClientLogicMarker
    fun Number.isGreaterThan(other: ClientLogicElement) = NumberElement(this).isGreaterThan(other)
}

private class GreaterThanOperatorFactory : OperatorFactory(">")
