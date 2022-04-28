package pl.allegro.logic.clientLogic.operators.arithmetic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.NumberElement

internal interface DivisionOperation {
    @ClientLogicMarker
    fun ClientLogicElement.divide(denominator: ClientLogicElement) = DivideOperatorFactory().create(this, denominator)

    @ClientLogicMarker
    fun ClientLogicElement.divide(denominator: Number) = divide(NumberElement(denominator))

    @ClientLogicMarker
    fun Number.divide(denominator: ClientLogicElement) = NumberElement(this).divide(denominator)
}

private class DivideOperatorFactory : OperatorFactory(name = "/")
