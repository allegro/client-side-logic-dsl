package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.NumberElement

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
