package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory
import pl.allegro.logic.BooleanElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.StringElement

internal interface StrictEqualOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isStrictEqual(other: ClientLogicElement) = StrictEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isStrictEqual(other: String) = isStrictEqual(StringElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isStrictEqual(other: Number) = isStrictEqual(NumberElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isStrictEqual(other: Boolean) = isStrictEqual(BooleanElement(other))
}

private class StrictEqualOperatorFactory : OperatorFactory("===")
