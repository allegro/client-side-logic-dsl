package pl.allegro.logic.clientLogic.operators.numeric

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.NumberElement

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
