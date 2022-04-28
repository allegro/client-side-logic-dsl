package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface LessOrEqualThanOperation {

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqualThan(other: ClientLogicElement) = LessOrEqualThanOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isLessOrEqualThan(other: Number) = isLessOrEqualThan(NumberElement(other))

    @ClientLogicMarker
    fun Int.isLessOrEqualThan(other: ClientLogicElement) = NumberElement(this).isLessOrEqualThan(other)
}

internal class LessOrEqualThanOperatorFactory : OperatorFactory("<=")
