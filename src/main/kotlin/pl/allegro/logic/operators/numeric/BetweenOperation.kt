package pl.allegro.logic.operators.numeric

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.NumberElement

internal interface BetweenOperation {

    @ClientLogicMarker
    fun ClientLogicElement.between(min: ClientLogicElement, max: ClientLogicElement) =
        LessThanOperatorFactory().create(min, this, max)

    @ClientLogicMarker
    fun ClientLogicElement.between(min: ClientLogicElement, max: Number) = between(min, NumberElement(max))

    @ClientLogicMarker
    fun ClientLogicElement.between(min: Number, max: ClientLogicElement) = between(NumberElement(min), max)

    @ClientLogicMarker
    fun ClientLogicElement.between(min: Number, max: Number) = between(NumberElement(min), NumberElement(max))
}
