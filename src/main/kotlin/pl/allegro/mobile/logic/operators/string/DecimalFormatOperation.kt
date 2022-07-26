package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.BooleanElement
import pl.allegro.mobile.logic.ListOfClientElements

internal interface DecimalFormatOperation {
    /**
     * Formats text with provided arguments.
     * @receiver Character sequence or client side operation that returns string
     * @param formatString client side data or operation results which will be used as format string
     * @param args floating point arguments to be inserted into formatted string
     * @return format operator, evaluated client side.
     * Operator returns this string with applied format params.
     * @see: DecimalFormatOperationTest
     */
    @ClientLogicMarker
    fun decimalFormat(formatString: String, vararg args: Any) = DecimalFormatOperatorFactory().create(StringElement(formatString), args)

    @ClientLogicMarker
    fun ClientLogicElement.decimalFormat(vararg args: Any) = DecimalFormatOperatorFactory().create(this, args)
}

private class DecimalFormatOperatorFactory {
    fun create(
        formatString: ClientLogicElement,
        args: Array<out Any>
    ) = ClientLogicOperator.Builder("decimalFormat")
        .add(formatString)
        .add(args.toElements())
        .build()

    private fun Array<out Any>.toElements(): ListOfClientElements<ClientLogicElement> {
        val elementsListBuilder = ListOfClientElements.Builder()
        forEach {
            when (it) {
                is Number -> elementsListBuilder.add(NumberElement(it))
                is ClientLogicElement -> elementsListBuilder.add(it)
            }
        }
        return elementsListBuilder.build()
    }
}
