package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.*
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface FormatOperation {
    /**
     * Formats text with provided arguments.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence client side data or operation results which will be injected into string
     * @return format operator, evaluated client side.
     * Operator returns this string with applied format params.
     * @see: FormatOperationTest
     */
    @ClientLogicMarker
    fun format(sequence: ClientLogicElement) = FormatOperatorFactory().create(sequence)

    @ClientLogicMarker
    fun formatMsg(formatString: String, vararg args: ClientLogicElement) = FormatOperatorFactory().create(sequence)
}

private class FormatOperatorFactory {
    fun create(
        formatString: String,
        vararg args: Any?
    ) = ClientLogicOperator.Builder("format")
        .add(StringElement(formatString))
        .add(args?.let { ListOfClientElements(it.toList()) })
        .build()

    private fun elementsArray(vararg args: Any?) : ClientLogicArray<ClientLogicElement> {
        {
            args
        }
    }
}
