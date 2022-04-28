package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface GreaterThanOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isGreaterThan(other: ClientLogicElement) = GreaterThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isGreaterThan(other: Number) = isGreaterThan(NumberElement(other))

    @ClientLogicMarker
    fun Number.isGreaterThan(other: ClientLogicElement) = NumberElement(this).isGreaterThan(other)
}

private class GreaterThanOperatorFactory : OperatorFactory(">")
