package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.NullElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory
import pl.allegro.mobile.logic.BooleanElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.StringElement

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
