package pl.allegro.logic.operators.dataaccess

import pl.allegro.logic.BooleanElement
import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientRegistryDataElement
import pl.allegro.logic.ListOfClientElements
import pl.allegro.logic.NumberElement
import pl.allegro.logic.RegistryDataElement
import pl.allegro.logic.StringElement
import pl.allegro.logic.ClientLogicMarker

internal interface PrimitiveToElementConversion {

    @ClientLogicMarker
    fun registryKey(key: String): ClientRegistryDataElement =
        RegistryDataElement(key)

    @ClientLogicMarker
    fun registryKey(key: String, default: String): ClientRegistryDataElement =
        RegistryDataElement(key, StringElement(default))

    @ClientLogicMarker
    fun registryKey(key: String, default: Number): ClientRegistryDataElement =
        RegistryDataElement(key, NumberElement(default))

    @ClientLogicMarker
    fun registryKey(key: String, default: Boolean): ClientRegistryDataElement =
        RegistryDataElement(key, BooleanElement(default))

    @ClientLogicMarker
    fun listOfElements(vararg elements: ClientLogicElement): ListOfClientElements<ClientLogicElement> =
        ListOfClientElements(elements.toList())

    @ClientLogicMarker
    fun buildListOfElements(builder: (ListOfClientElements.Builder).() -> ListOfClientElements.Builder): ListOfClientElements<ClientLogicElement> =
        ListOfClientElements.Builder().builder().build()

    @ClientLogicMarker
    fun <T : ClientLogicElement> List<T>.toListOfElements(): ListOfClientElements<T> = ListOfClientElements(this)

    @ClientLogicMarker
    fun emptyListOfElements(): ListOfClientElements<out ClientLogicElement> = ListOfClientElements(emptyList())
}
