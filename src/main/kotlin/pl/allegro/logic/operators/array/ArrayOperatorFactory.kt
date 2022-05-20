package pl.allegro.logic.operators.array

import pl.allegro.logic.ClientLogic
import pl.allegro.logic.ClientLogicArray
import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicOperator
import pl.allegro.logic.ClientRegistryDataElement
import pl.allegro.logic.RegistryDataElement

internal abstract class ArrayOperatorFactory(protected val name: String) {

    fun <T : ClientLogicElement> create(
        element: ClientLogicArray<T>,
        transformation: ArrayTransformation
    ) = ClientLogicOperator(name, element, ClientLogic.transformation(allKeys()))

    private fun allKeys(): ClientRegistryDataElement = RegistryDataElement(key = "")
}

typealias ArrayTransformation = ClientLogic.(it: ClientRegistryDataElement) -> ClientLogicElement
