package pl.allegro.mobile.logic.operators.dataaccess

import pl.allegro.mobile.logic.BooleanElement
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientRegistryDataElement
import pl.allegro.mobile.logic.ListOfClientElements
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.RegistryDataElement
import pl.allegro.mobile.logic.StringElement

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
