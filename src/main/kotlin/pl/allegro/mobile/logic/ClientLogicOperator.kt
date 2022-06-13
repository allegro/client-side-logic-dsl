package pl.allegro.mobile.logic

internal open class ClientLogicOperator internal constructor(
    val name: String,
    val arguments: List<ClientLogicElement> = emptyList()
) : ClientLogicElement, ClientLogicArray<ClientLogicElement> {

    internal constructor(name: String, vararg arguments: ClientLogicElement) : this(name, arguments.toList())

    internal class Builder(private val name: String) {

        private val arguments = mutableListOf<ClientLogicElement>()

        fun add(element: ClientLogicElement) = apply {
            arguments.add(element)
        }

        fun addIfNotNull(element: ClientLogicElement?) = apply {
            element?.let { arguments.add(element) }
        }

        fun addAll(elements: List<ClientLogicElement>) = apply {
            arguments.addAll(elements)
        }

        fun addAll(elements: Array<out ClientLogicElement>) = apply {
            arguments.addAll(elements)
        }

        fun build() = ClientLogicOperator(name, arguments)
    }
}
