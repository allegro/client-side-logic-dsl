package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface LessOrEqualThanOperation {

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqualThan(other: ClientLogicElement) = LessOrEqualThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqualThan(other: Number) = isLessOrEqualThan(NumberElement(other))

    @ClientLogicMarker
    fun Int.isLessOrEqualThan(other: ClientLogicElement) = NumberElement(this).isLessOrEqualThan(other)
}

internal class LessOrEqualThanOperatorFactory : OperatorFactory("<=")
