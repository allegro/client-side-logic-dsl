package pl.allegro.logic.operators.arithmetic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ListOfClientElements
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.FlattenableOperatorFactory

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
