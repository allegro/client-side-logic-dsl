package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface LessThanOperation {

    @ClientLogicMarker
    fun ClientLogicElement.isLessThan(other: ClientLogicElement) = LessThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isLessThan(other: Number) = isLessThan(NumberElement(other))

    @ClientLogicMarker
    fun Number.isLessThan(other: ClientLogicElement) = NumberElement(this).isLessThan(other)
}

internal class LessThanOperatorFactory : OperatorFactory("<")
