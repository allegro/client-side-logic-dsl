package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface LessThanOperation {

    @ClientLogicMarker
    fun ClientLogicElement.isLessThan(other: ClientLogicElement) = LessThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isLessThan(other: Number) = isLessThan(NumberElement(other))

    @ClientLogicMarker
    fun Number.isLessThan(other: ClientLogicElement) = NumberElement(this).isLessThan(other)
}

internal class LessThanOperatorFactory : OperatorFactory("<")
