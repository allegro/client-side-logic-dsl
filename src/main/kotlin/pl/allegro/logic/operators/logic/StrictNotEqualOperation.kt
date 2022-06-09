package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory
import pl.allegro.logic.BooleanElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.StringElement

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
