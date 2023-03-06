package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface MatchOperation {
    /**
     * Check if a string matches to given regex pattern
     * @receiver Character sequence or client side operation that returns string
     * @param regexPattern regex pattern which we want to use
     * @param regexOptions regex additional options
     * @return match operator, evaluated client side.
     * Returns true or false depending on if the string matches to given regex pattern.
     * @see: MatchOperationTest
     */
    @ClientLogicMarker
    fun ClientLogicElement.match(regexPattern: String, regexOptions: List<RegexOption> = emptyList()) =
        MatchOperatorFactory().create(this, regexPattern, regexOptions)
}

private class MatchOperatorFactory {
    fun create(
        element: ClientLogicElement,
        regexPattern: String,
        regexOptions: List<RegexOption>,
    ) = ClientLogicOperator.Builder("match")
        .add(element)
        .add(StringElement(regexPattern))
        .add(createFromRegexOptions(regexOptions))
        .build()

    fun createFromRegexOptions(regexOptions: List<RegexOption>): ClientLogicArray<ClientLogicElement> =
        if (regexOptions.isNullOrEmpty()) {

        } else {
            ClientLogicOperator("regexOptions", regexOptions.distinct().map { StringElement(it.regexOption) })
        }
}

enum class RegexOption(val regexOption: String) {
    IGNORE_CASE("IGNORE_CASE"),
    MULTILINE("MULTILINE")
}
