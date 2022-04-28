package pl.allegro.logic.clientLogic.operators.dataaccess

import pl.allegro.logic.clientLogic.BooleanElement
import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ClientRegistryDataElement
import pl.allegro.logic.clientLogic.ListOfClientElements
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.RegistryDataElement
import pl.allegro.logic.clientLogic.StringElement

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
