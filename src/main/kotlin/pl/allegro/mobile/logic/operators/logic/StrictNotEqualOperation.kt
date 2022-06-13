package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory
import pl.allegro.mobile.logic.BooleanElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.StringElement

internal interface StrictNotEqualOperation {
    @ClientLogicMarker
    fun ClientLogicElement.isStrictNotEqual(other: ClientLogicElement) = StrictNotEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.isStrictNotEqual(other: String) = isStrictNotEqual(StringElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isStrictNotEqual(other: Number) = isStrictNotEqual(NumberElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.isStrictNotEqual(other: Boolean) = isStrictNotEqual(BooleanElement(other))
}

private class StrictNotEqualOperatorFactory : OperatorFactory("!==")
