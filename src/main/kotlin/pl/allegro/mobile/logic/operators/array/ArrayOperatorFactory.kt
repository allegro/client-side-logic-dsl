package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogic
import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.ClientRegistryDataElement
import pl.allegro.mobile.logic.RegistryDataElement

internal abstract class ArrayOperatorFactory(protected val name: String) {

    fun <T : ClientLogicElement> create(
        element: ClientLogicArray<T>,
        transformation: ArrayTransformation
    ) = ClientLogicOperator(name, element, ClientLogic.transformation(allKeys()))

    private fun allKeys(): ClientRegistryDataElement = RegistryDataElement(key = "")
}

typealias ArrayTransformation = ClientLogic.(it: ClientRegistryDataElement) -> ClientLogicElement
