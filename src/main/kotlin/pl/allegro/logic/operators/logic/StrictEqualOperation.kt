package pl.allegro.logic.operators.logic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory
import pl.allegro.logic.BooleanElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.StringElement

internal interface StrictEqualOperation {
    @ClientLogicMarker
    fun ClientLogicElement.strictEqual(other: ClientLogicElement) = StrictEqualOperatorFactory().create(this, other)

    @ClientLogicMarker
    fun ClientLogicElement.strictEqual(other: String) = strictEqual(StringElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.strictEqual(other: Number) = strictEqual(NumberElement(other))

    @ClientLogicMarker
    fun ClientLogicElement.strictEqual(other: Boolean) = strictEqual(BooleanElement(other))
}

private class StrictEqualOperatorFactory : OperatorFactory("===")
