package pl.allegro.mobile.logic.operators.arithmetic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface ModuloOperation {
    @ClientLogicMarker
    fun ClientLogicElement.modulo(divisor: ClientLogicElement) = ModuloOperatorFactory().create(this, divisor)

    @ClientLogicMarker
    fun ClientLogicElement.modulo(divisor: Number) = modulo(NumberElement(divisor))

    @ClientLogicMarker
    fun Number.modulo(divisor: ClientLogicElement) = NumberElement(this).modulo(divisor)
}

private class ModuloOperatorFactory : OperatorFactory(name = "%")
