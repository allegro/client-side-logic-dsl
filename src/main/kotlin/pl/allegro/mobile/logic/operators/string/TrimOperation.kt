package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface TrimOperation {
    /**
     * based on trim mode removes given character from the declared end of the string.
     * @receiver Character sequence or client side operation that returns string
     * @param sequence client side data or operation results which will be injected to string
     * @param character to remove from the given end of sequence
     * @param trimMode indicates direction of trimming
     * @return trim operator, evaluated client side.
     * Operator returns a copy of this string trimmed with given character
     * @see: TrimOperationTest
     */
    @ClientLogicMarker
    fun trim(sequence: ClientLogicElement, character: Char = ' ', trimMode: TrimMode = TrimMode.BOTH_ENDS) =
        TrimOperatorFactory().create(sequence, character, trimMode)

    @ClientLogicMarker
    fun ClientLogicElement.trimmed(character: Char = ' ', trimMode: TrimMode = TrimMode.BOTH_ENDS) =
        TrimOperatorFactory().create(this, character, trimMode)
}

private class TrimOperatorFactory {

    fun create(
        element: ClientLogicElement,
        character: Char,
        trimMode: TrimMode
    ) = ClientLogicOperator.Builder("trim")
        .add(element)
        .add(StringElement(character.toString()))
        .add(StringElement(trimMode.mode))
        .build()
}

enum class TrimMode(val mode: String) {
    START("start"),
    END("end"),
    BOTH_ENDS("bothEnds")
}
