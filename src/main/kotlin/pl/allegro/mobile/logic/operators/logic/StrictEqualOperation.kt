package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory
import pl.allegro.mobile.logic.BooleanElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.StringElement

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
