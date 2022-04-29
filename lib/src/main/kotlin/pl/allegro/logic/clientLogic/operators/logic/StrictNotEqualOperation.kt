package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.BooleanElement
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.StringElement

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
