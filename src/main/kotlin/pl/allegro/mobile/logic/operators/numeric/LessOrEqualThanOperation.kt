package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface LessOrEqualThanOperation {

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqualThan(other: ClientLogicElement) = LessOrEqualThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqualThan(other: Number) = isLessOrEqualThan(NumberElement(other))

    @ClientLogicMarker
    fun Int.isLessOrEqualThan(other: ClientLogicElement) = NumberElement(this).isLessOrEqualThan(other)
}

internal class LessOrEqualThanOperatorFactory : OperatorFactory("<=")
