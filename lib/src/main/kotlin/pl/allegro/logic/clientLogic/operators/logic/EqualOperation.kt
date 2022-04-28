package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.BooleanElement
import pl.allegro.logic.clientLogic.NullElement
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.StringElement

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
