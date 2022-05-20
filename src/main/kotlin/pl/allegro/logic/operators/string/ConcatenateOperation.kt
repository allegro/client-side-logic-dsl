package pl.allegro.logic.operators.string

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.StringElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.FlattenableOperatorFactory

internal interface ConcatenateOperation {

    /**
     * Concatenate string with client data or client side operation results.
     * @param format format of the string. Use %s to inject string.
     * Inspired by [String.format](https://www.javatpoint.com/java-string-format).
     * Formatting numbers (e.g. %.2f) will be added in the future.
     * @param args client side data or operation results which will be injected to string
     * @return [cat operator](https://jsonlogic.com/operations.html#cat), evaluated client side.
     * Operator returns concatenated string.
     * @see: ConcatOperationTest
     */
    @ClientLogicMarker
    fun concat(format: String, vararg args: ClientLogicElement): ClientLogicElement {
        val builder = ConcatenateOperatorBuilder()
        format.split(STRING_PLACEHOLDER).forEachIndexed { index, textPart ->
            args.getOrNull(index - 1)?.let { builder.add(it) }
            if (textPart.isNotEmpty()) {
                builder.add(textPart)
            }
        }
        return builder.build()
    }

    companion object {
        const val STRING_PLACEHOLDER = "%s"
    }
}

private class ConcatenateOperatorBuilder {
    val elements = mutableListOf<ClientLogicElement>()

    fun add(element: ClientLogicElement) = apply {
        elements.add(element)
    }

    fun add(text: String) = apply {
        elements.add(StringElement(text))
    }

    fun build() = when (elements.size) {
        1 -> elements.first()
        else -> ConcatenateStringOperatorFactory().create(elements)
    }
}

private class ConcatenateStringOperatorFactory : FlattenableOperatorFactory("cat")
