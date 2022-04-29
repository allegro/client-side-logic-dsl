package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.BooleanElement
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.StringElement

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
