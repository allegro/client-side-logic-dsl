package pl.allegro.mobile.logic.operators.arithmetic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface DivisionOperation {
    @ClientLogicMarker
    fun ClientLogicElement.divide(denominator: ClientLogicElement) = DivideOperatorFactory().create(this, denominator)

    @ClientLogicMarker
    fun ClientLogicElement.divide(denominator: Number) = divide(NumberElement(denominator))

    @ClientLogicMarker
    fun Number.divide(denominator: ClientLogicElement) = NumberElement(this).divide(denominator)
}

private class DivideOperatorFactory : OperatorFactory(name = "/")
