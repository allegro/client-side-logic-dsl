package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface LessThanOperation {

    @ClientLogicMarker
    fun ClientLogicElement.isLessThan(other: ClientLogicElement) = LessThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isLessThan(other: Number) = isLessThan(NumberElement(other))

    @ClientLogicMarker
    fun Number.isLessThan(other: ClientLogicElement) = NumberElement(this).isLessThan(other)
}

internal class LessThanOperatorFactory : OperatorFactory("<")