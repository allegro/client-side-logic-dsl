package pl.allegro.logic.clientLogic

interface ClientLogicElement

internal class StringElement(val value: String) : ClientLogicElement
internal class BooleanElement(val value: Boolean) : ClientLogicElement
internal object NullElement : ClientLogicElement
internal class NumberElement(valueNumber: Number) : ClientLogicElement {
    val value = valueNumber.toString()
}

internal class RegistryDataElement(internal val key: String, default: ClientLogicElement? = null) :
    ClientRegistryDataElement,
    ClientLogicOperator(name = "var", listOfNotNull(StringElement(key), default))

interface ClientRegistryDataElement : ClientLogicElement

interface ClientLogicArray<T : ClientLogicElement> : ClientLogicElement
