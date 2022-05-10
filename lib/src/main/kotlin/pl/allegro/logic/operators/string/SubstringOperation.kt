package pl.allegro.logic.operators.string

import pl.allegro.logic.ClientLogic.isEmpty
import pl.allegro.logic.ClientLogic.isEqual
import pl.allegro.logic.ClientLogic.isNotEmpty
import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicOperator
import pl.allegro.logic.ClientRegistryDataElement
import pl.allegro.logic.NumberElement
import pl.allegro.logic.ClientLogicMarker


internal interface SubstringOperation {

    @ClientLogicMarker
    fun substring(element: ClientLogicElement, startFromIndex: Int, numOfCharacters: Int? = null) =
        SubstringOperatorFactory().create(element, startFromIndex, numOfCharacters)

    @ClientLogicMarker
    fun ClientRegistryDataElement.startsWith(text: String) =
        substring(this, startFromIndex = 0, numOfCharacters = text.length).isEqual(text)

    @ClientLogicMarker
    fun ClientRegistryDataElement.endsWith(text: String) =
        substring(this, startFromIndex = (-1 * text.length)).isEqual(text)

    @ClientLogicMarker
    fun ClientRegistryDataElement.lengthIsAtLeast(minLength: Int) =
        substring(this, startFromIndex = minLength - 1).isNotEmpty()

    @ClientLogicMarker
    fun ClientRegistryDataElement.lengthIsAtMost(maxLength: Int) =
        substring(this, startFromIndex = maxLength).isEmpty()
}

private class SubstringOperatorFactory {

    fun create(
        element: ClientLogicElement,
        startFromIndex: Int,
        numOfCharacters: Int? = null
    ) = ClientLogicOperator.Builder("substr")
        .add(element)
        .add(NumberElement(startFromIndex))
        .addIfNotNull(numOfCharacters?.let { NumberElement(it) })
        .build()
}
