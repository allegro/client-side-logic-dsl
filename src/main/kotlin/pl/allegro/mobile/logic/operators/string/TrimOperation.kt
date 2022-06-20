package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface TrimOperation {
    /**
     * Converts the character sequence to lower case.
     * @param sequence client side data or operation results which will be injected to string
     * @return lowercase operator, evaluated client side.
     * Operator returns a copy of this string converted to lower case.
     * @see: LowercaseOperationTest
     */
    @ClientLogicMarker
    fun trim(sequence: ClientLogicElement, character: Char?, trimMode: TrimMode = TrimMode.BOTH_ENDS) = TrimOperatorFactory().create(sequence, character, trimMode)

    @ClientLogicMarker
    fun ClientLogicElement.trimmed(character: Char?, trimMode: TrimMode = TrimMode.BOTH_ENDS) = TrimOperatorFactory().create(this, character, trimMode)
}

private class TrimOperatorFactory {

    fun create(
        element: ClientLogicElement,
        character: Char?,
        trimMode: TrimMode
    ) = ClientLogicOperator.Builder("trim")
        .add(element)
        .add(StringElement(character?.toString() ?: " "))
        .add(StringElement(trimMode.mode))
        .build()
}

internal enum class TrimMode(val mode: String) {
    START("start"),
    END("end"),
    BOTH_ENDS("bothEnds")
}