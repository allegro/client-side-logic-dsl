package pl.allegro.mobile.logic

class ListOfClientElements<T : ClientLogicElement>(val values: List<T>) : ClientLogicElement, ClientLogicArray<T> {

    class Builder {
        private val elements = mutableListOf<ClientLogicElement>()
        fun add(element: ClientLogicElement) = apply { elements.add(element) }
        fun add(element: String) = apply { elements.add(StringElement(element)) }
        fun add(element: Number) = apply { elements.add(NumberElement(element)) }
        fun add(element: Boolean) = apply { elements.add(BooleanElement(element)) }
        fun build() = ListOfClientElements(elements)
    }
}
