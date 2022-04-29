package pl.allegro.logic.clientLogic.operators.arithmetic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.NumberElement

internal interface SubtractionOperation {
    @ClientLogicMarker
    fun ClientLogicElement.minus(subtrahend: ClientLogicElement) = MinusOperatorFactory().create(this, subtrahend)

    @ClientLogicMarker
    fun ClientLogicElement.minus(subtrahend: Number) = minus(NumberElement(subtrahend))

    @ClientLogicMarker
    fun Number.minus(subtrahend: ClientLogicElement) = NumberElement(this).minus(subtrahend)

    @ClientLogicMarker
    fun ClientLogicElement.negateNumber() = MinusOperatorFactory().create(this)
}

private class MinusOperatorFactory : OperatorFactory(name = "-")
