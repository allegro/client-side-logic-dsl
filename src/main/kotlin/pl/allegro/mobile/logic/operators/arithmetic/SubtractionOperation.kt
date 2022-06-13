package pl.allegro.mobile.logic.operators.arithmetic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.operators.OperatorFactory

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
