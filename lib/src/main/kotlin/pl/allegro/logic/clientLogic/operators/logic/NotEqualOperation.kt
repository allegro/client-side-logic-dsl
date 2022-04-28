package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.BooleanElement
import pl.allegro.logic.clientLogic.NullElement
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.StringElement

internal interface NotEqualOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isNotEqual(other: ClientLogicElement) = NotEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isNotEqual(other: String) = isNotEqual(StringElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isNotEqual(other: Number) = isNotEqual(NumberElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isNotEqual(other: Boolean) = isNotEqual(BooleanElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isNotNull() = isNotEqual(NullElement)
}

private class NotEqualOperatorFactory : OperatorFactory("!=")
