package pl.allegro.logic.operators.arithmetic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface DivisionOperation {
    @ClientLogicMarker
    fun ClientLogicElement.divide(denominator: ClientLogicElement) = DivideOperatorFactory().create(this, denominator)

    @ClientLogicMarker
    fun ClientLogicElement.divide(denominator: Number) = divide(NumberElement(denominator))

    @ClientLogicMarker
    fun Number.divide(denominator: ClientLogicElement) = NumberElement(this).divide(denominator)
}

private class DivideOperatorFactory : OperatorFactory(name = "/")
