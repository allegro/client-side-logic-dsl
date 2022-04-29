package pl.allegro.logic.clientLogic.operators.arithmetic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory
import pl.allegro.logic.clientLogic.NumberElement

internal interface ModuloOperation {
    @ClientLogicMarker
    fun ClientLogicElement.modulo(divisor: ClientLogicElement) = ModuloOperatorFactory().create(this, divisor)

    @ClientLogicMarker
    fun ClientLogicElement.modulo(divisor: Number) = modulo(NumberElement(divisor))

    @ClientLogicMarker
    fun Number.modulo(divisor: ClientLogicElement) = NumberElement(this).modulo(divisor)
}

private class ModuloOperatorFactory : OperatorFactory(name = "%")
