package pl.allegro.logic.clientLogic.operators.arithmetic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.FlattenableOperatorFactory
import pl.allegro.logic.clientLogic.NumberElement

internal interface MultiplicationOperation {
    @ClientLogicMarker
    fun ClientLogicElement.multiply(factor: ClientLogicElement) = MultiplyOperatorFactory().create(this, factor)

    @ClientLogicMarker
    fun ClientLogicElement.multiply(factor: Number) = multiply(NumberElement(factor))

    @ClientLogicMarker
    fun Number.multiply(factor: ClientLogicElement) = NumberElement(this).multiply(factor)
}

private class MultiplyOperatorFactory : FlattenableOperatorFactory(name = "*")
