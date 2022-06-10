package pl.allegro.mobile.logic.operators.dataaccess

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface MissingOperation {
    /**
     * Checks if there is data defined under given registry keys
     * @param registryKeys registry keys to search for
     * @return [missing operator](https://jsonlogic.com/operations.html#missing), evaluated client side.
     * operator returns an array of any keys that are missing from the data object, or an empty array.
     * @see: MissingOperationTest
     */
    @ClientLogicMarker
    fun missing(vararg registryKeys: String) =
        MissingOperatorFactory().createFromKeys(listOf(*registryKeys))

    @ClientLogicMarker
    fun missing(expression: ClientLogicArray<*>) = MissingOperatorFactory().createFromElement(expression)

    @ClientLogicMarker
    fun missing(registryKeys: List<String>) = MissingOperatorFactory().createFromKeys(registryKeys)
}

private class MissingOperatorFactory {
    fun createFromKeys(keys: List<String>): ClientLogicArray<ClientLogicElement> =
        ClientLogicOperator(MISSING_OPERATOR_NAME, keys.map { StringElement(it) })

    fun createFromElement(element: ClientLogicElement) = ClientLogicOperator(MISSING_OPERATOR_NAME, element)
}

private const val MISSING_OPERATOR_NAME = "missing"
