package pl.allegro.mobile.logic.operators.arithmetic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.operators.FlattenableOperatorFactory

internal interface MultiplicationOperation {
    @ClientLogicMarker
    fun ClientLogicElement.multiply(factor: ClientLogicElement) = MultiplyOperatorFactory().create(this, factor)

    @ClientLogicMarker
    fun ClientLogicElement.multiply(factor: Number) = multiply(NumberElement(factor))

    @ClientLogicMarker
    fun Number.multiply(factor: ClientLogicElement) = NumberElement(this).multiply(factor)
}

private class MultiplyOperatorFactory : FlattenableOperatorFactory(name = "*")
