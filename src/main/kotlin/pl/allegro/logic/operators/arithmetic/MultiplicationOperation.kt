package pl.allegro.logic.operators.arithmetic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.FlattenableOperatorFactory

internal interface MultiplicationOperation {
    @ClientLogicMarker
    fun ClientLogicElement.multiply(factor: ClientLogicElement) = MultiplyOperatorFactory().create(this, factor)

    @ClientLogicMarker
    fun ClientLogicElement.multiply(factor: Number) = multiply(NumberElement(factor))

    @ClientLogicMarker
    fun Number.multiply(factor: ClientLogicElement) = NumberElement(this).multiply(factor)
}

private class MultiplyOperatorFactory : FlattenableOperatorFactory(name = "*")
