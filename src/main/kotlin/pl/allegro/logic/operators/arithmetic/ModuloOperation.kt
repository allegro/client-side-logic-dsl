package pl.allegro.logic.operators.arithmetic

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface ModuloOperation {
    @ClientLogicMarker
    fun ClientLogicElement.modulo(divisor: ClientLogicElement) = ModuloOperatorFactory().create(this, divisor)

    @ClientLogicMarker
    fun ClientLogicElement.modulo(divisor: Number) = modulo(NumberElement(divisor))

    @ClientLogicMarker
    fun Number.modulo(divisor: ClientLogicElement) = NumberElement(this).modulo(divisor)
}

private class ModuloOperatorFactory : OperatorFactory(name = "%")
