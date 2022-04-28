package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.NumberElement

internal interface BetweenOrEqualOperation {
    @ClientLogicMarker
    fun ClientLogicElement.betweenOrEqual(min: ClientLogicElement, max: ClientLogicElement) =
        LessOrEqualThanOperatorFactory().create(min, this, max)

    @ClientLogicMarker
    fun ClientLogicElement.betweenOrEqual(min: ClientLogicElement, max: Number) = betweenOrEqual(min, NumberElement(max))

    @ClientLogicMarker
    fun ClientLogicElement.betweenOrEqual(min: Number, max: ClientLogicElement) = betweenOrEqual(NumberElement(min), max)

    @ClientLogicMarker
    fun ClientLogicElement.betweenOrEqual(min: Number, max: Number) =
        betweenOrEqual(NumberElement(min), NumberElement(max))
}
