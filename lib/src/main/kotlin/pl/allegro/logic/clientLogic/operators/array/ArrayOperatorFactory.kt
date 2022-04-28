package pl.allegro.logic.clientLogic.operators.array

import pl.allegro.logic.clientLogic.ClientLogic
import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicOperator
import pl.allegro.logic.clientLogic.ClientRegistryDataElement
import pl.allegro.logic.clientLogic.RegistryDataElement
import pl.allegro.logic.clientLogic.ClientLogicArray

internal abstract class ArrayOperatorFactory(protected val name: String) {

    fun <T : ClientLogicElement> create(
        element: ClientLogicArray<T>,
        transformation: ArrayTransformation
    ) = ClientLogicOperator(name, element, ClientLogic.transformation(allKeys()))

    private fun allKeys(): ClientRegistryDataElement = RegistryDataElement(key = "")
}

typealias ArrayTransformation = ClientLogic.(it: ClientRegistryDataElement) -> ClientLogicElement
