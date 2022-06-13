package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.BooleanElement
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.NullElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.StringElement
import pl.allegro.mobile.logic.operators.OperatorFactory

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
