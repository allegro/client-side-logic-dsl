package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NullElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory
import pl.allegro.logic.BooleanElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.StringElement

internal interface EqualOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isEqual(other: ClientLogicElement) = EqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isEqual(other: String) = isEqual(StringElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isEqual(other: Number) = isEqual(NumberElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isEqual(other: Boolean) = isEqual(BooleanElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isNull() = isEqual(NullElement)
}

private class EqualOperatorFactory : OperatorFactory("==")
