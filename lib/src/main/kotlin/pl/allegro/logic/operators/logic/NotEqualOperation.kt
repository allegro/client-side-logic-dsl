package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NullElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory
import pl.allegro.logic.BooleanElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.StringElement

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
