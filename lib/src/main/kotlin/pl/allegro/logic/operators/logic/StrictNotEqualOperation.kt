package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory
import pl.allegro.logic.BooleanElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.StringElement

internal interface StrictNotEqualOperation {
    @ClientLogicMarker
    fun ClientLogicElement.strictNotEqual(other: ClientLogicElement) = StrictNotEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.strictNotEqual(other: String) = strictNotEqual(StringElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.strictNotEqual(other: Number) = strictNotEqual(NumberElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.strictNotEqual(other: Boolean) = strictNotEqual(BooleanElement(other))
}

private class StrictNotEqualOperatorFactory : OperatorFactory("!==")
