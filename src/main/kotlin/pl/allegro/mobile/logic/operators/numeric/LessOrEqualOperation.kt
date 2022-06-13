package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface LessOrEqualOperation {

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqual(other: ClientLogicElement) = LessOrEqualThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqual(other: Number) = isLessOrEqual(NumberElement(other))

    @ClientLogicMarker
    fun Int.isLessOrEqual(other: ClientLogicElement) = NumberElement(this).isLessOrEqual(other)
}

internal class LessOrEqualThanOperatorFactory : OperatorFactory("<=")
