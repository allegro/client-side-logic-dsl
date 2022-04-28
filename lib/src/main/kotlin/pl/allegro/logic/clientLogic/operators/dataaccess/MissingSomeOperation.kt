package pl.allegro.logic.clientLogic.operators.dataaccess

import pl.allegro.logic.clientLogic.ClientLogicArray
import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ClientLogicOperator
import pl.allegro.logic.clientLogic.ListOfClientElements
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.StringElement

internal interface MissingSomeOperation {

    /**
     * Checks if there is data defined under given registry keys
     * @param requiredCount minimum number of elements that need to be found
     * @param registryKeys registry keys to search for
     * @return [missing some operator](https://jsonlogic.com/operations.html#missing_some), evaluated client side.
     * operator returns empty array if the minimum is met, or an array of the missing keys otherwise.
     * @see: MissingSomeOperationTest
     */
    @ClientLogicMarker
    fun missingSome(requiredCount: Int, vararg registryKeys: String) =
        MissingSomeOperatorFactory().createFromKeys(requiredCount, listOf(*registryKeys))

    @ClientLogicMarker
    fun missingSome(requiredCount: Int, element: ClientLogicArray<*>) =
        MissingSomeOperatorFactory().createFromElement(requiredCount, element)

    @ClientLogicMarker
    fun missingSome(requiredCount: Int, registryKeys: List<String>) =
        MissingSomeOperatorFactory().createFromKeys(requiredCount, registryKeys)
}

private class MissingSomeOperatorFactory {

    fun createFromKeys(requiredCount: Int, keys: List<String>) = ClientLogicOperator(
        MISSING_SOME_OPERATOR_NAME,
        listOf(NumberElement(requiredCount), ListOfClientElements(keys.map { StringElement(it) }))
    )

    fun createFromElement(requiredCount: Int, element: ClientLogicElement) =
        ClientLogicOperator(MISSING_SOME_OPERATOR_NAME, NumberElement(requiredCount), element)
}

private const val MISSING_SOME_OPERATOR_NAME = "missing_some"
