package pl.allegro.mobile.logic.operators.numeric

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface DecimalFormatOperation {
    /**
     * Formats decimal number with provided length arguments.
     * @receiver Character sequence, number or client side operation that returns one of them.
     * @param minWidth minimum number of characters in width of formatted element
     * @param decimalPlaces number of decimal places
     * @return decimalFormat operator, evaluated client side.
     * Operator returns this number of string formatted as decimal.
     * @see: DecimalFormatOperationTest
     */
    @ClientLogicMarker
    fun decimalFormat(
        element: ClientLogicElement,
        minWidth: DecimalFormatLength = DecimalFormatLength.Unmodified,
        decimalPlaces: DecimalFormatLength
    ) = DecimalFormatOperatorFactory().create(element, minWidth, decimalPlaces)

    @ClientLogicMarker
    fun ClientLogicElement.formatDecimal(
        minWidth: DecimalFormatLength = DecimalFormatLength.Unmodified,
        decimalPlaces: DecimalFormatLength
    ) = DecimalFormatOperatorFactory().create(this, minWidth, decimalPlaces)
}

private class DecimalFormatOperatorFactory {
    fun create(
        element: ClientLogicElement,
        minStringWidth: DecimalFormatLength,
        decimalPlaces: DecimalFormatLength
    ) = ClientLogicOperator.Builder("decimalFormat")
        .add(resolveFormatString(minStringWidth, decimalPlaces))
        .add(element)
        .build()

    private fun resolveFormatString(
        stringWidth: DecimalFormatLength,
        decimalPlaces: DecimalFormatLength
    ): StringElement {
        val widthSize: () -> String = { stringWidth.toStringSize() }
        val placesSize: () -> String = { decimalPlaces.toStringSize() }

        val formatString = when {
           (stringWidth is DecimalFormatLength.Exact && stringWidth.width == 0) -> "%.${placesSize()}f"
           decimalPlaces is DecimalFormatLength.Unmodified                      -> "%${widthSize()}f"
           else                                                                 -> "%${widthSize()}.${placesSize()}f"
        }
        return StringElement(formatString)
    }

    private fun DecimalFormatLength.toStringSize() = when (this) {
        is DecimalFormatLength.Unmodified -> ""
        is DecimalFormatLength.Exact      -> width.toString()
    }
}

sealed class DecimalFormatLength {
    object Unmodified : DecimalFormatLength()
    data class Exact(val width: Int) : DecimalFormatLength()
}
