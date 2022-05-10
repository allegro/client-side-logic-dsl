package pl.allegro.logic.operators.arithmetic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

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
