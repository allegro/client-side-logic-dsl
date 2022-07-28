package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface DecimalFormatOperation {
    /**
     * Formats decimal number with provided length arguments.
     * @receiver Character sequence, number or client side operation that returns one of them.
     * @param width number of characters in width of formatted element
     * @param decimalPlaces number of decimal places
     * @return decimalFormat operator, evaluated client side.
     * Operator returns this number of string formatted as decimal.
     * @see: DecimalFormatOperationTest
     */
    @ClientLogicMarker
    fun decimalFormat(element: ClientLogicElement, width: DecimalFormatLength = DecimalFormatLength.Unmodified, decimalPlaces: DecimalFormatLength) =
        DecimalFormatOperatorFactory().create(element, width, decimalPlaces)

    @ClientLogicMarker
    fun ClientLogicElement.formatDecimal(width: DecimalFormatLength = DecimalFormatLength.Unmodified, decimalPlaces: DecimalFormatLength) =
        DecimalFormatOperatorFactory().create(this, width, decimalPlaces)
}

private class DecimalFormatOperatorFactory {
    fun create(
        element: ClientLogicElement,
        stringWidth: DecimalFormatLength,
        decimalPlaces: DecimalFormatLength
    ) = ClientLogicOperator.Builder("decimalFormat")
        .add(resolveFormatString(stringWidth, decimalPlaces))
        .add(element)
        .build()

    private fun resolveFormatString(
        stringWidth: DecimalFormatLength,
        decimalPlaces: DecimalFormatLength
    ): StringElement {
        val formatString = if (decimalPlaces is DecimalFormatLength.Unmodified) {
            "%${stringWidth.toStringSize()}f"
        } else {
            "%${stringWidth.toStringSize()}.${decimalPlaces.toStringSize()}f"
        }

        return StringElement(formatString)
    }

    private fun DecimalFormatLength.toStringSize() = when (this) {
        is DecimalFormatLength.Unmodified -> ""
        is DecimalFormatLength.Exact      -> size.toString()
    }
}

sealed class DecimalFormatLength {
    object Unmodified : DecimalFormatLength()
    data class Exact(val size: Int) : DecimalFormatLength()
}
