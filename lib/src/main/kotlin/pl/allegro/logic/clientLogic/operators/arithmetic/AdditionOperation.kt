package pl.allegro.logic.clientLogic.operators.arithmetic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.ListOfClientElements
import pl.allegro.logic.clientLogic.operators.FlattenableOperatorFactory
import pl.allegro.logic.clientLogic.NumberElement

internal interface AdditionOperation {

    @ClientLogicMarker
    fun ClientLogicElement.plus(term: ClientLogicElement) = PlusOperatorFactory().create(this, term)

    @ClientLogicMarker
    fun ClientLogicElement.plus(term: Number) = plus(NumberElement(term))

    @ClientLogicMarker
    fun Number.plus(term: ClientLogicElement) = NumberElement(this).plus(term)

    @ClientLogicMarker
    fun ClientLogicElement.castToNumber() = PlusOperatorFactory().create(this)

    @ClientLogicMarker
    fun ListOfClientElements<ClientLogicElement>.sum() = PlusOperatorFactory().create(this.values)
}

private class PlusOperatorFactory : FlattenableOperatorFactory(name = "+")
