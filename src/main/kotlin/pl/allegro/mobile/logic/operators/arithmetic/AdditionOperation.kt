package pl.allegro.mobile.logic.operators.arithmetic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ListOfClientElements
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.operators.FlattenableOperatorFactory

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
