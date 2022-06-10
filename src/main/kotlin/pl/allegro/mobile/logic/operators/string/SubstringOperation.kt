package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogic.isEmpty
import pl.allegro.mobile.logic.ClientLogic.isEqual
import pl.allegro.mobile.logic.ClientLogic.isNotEmpty
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.ClientRegistryDataElement
import pl.allegro.mobile.logic.NumberElement

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
