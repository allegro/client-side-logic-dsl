package pl.allegro.mobile.logic.operators.array

import pl.allegro.mobile.logic.ClientLogicArray
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.NumberElement
import pl.allegro.mobile.logic.StringElement

internal interface JoinToStringOperation {
    /**
     * Creates a string from all the elements separated using separator and using the given prefix and postfix if supplied.
     * @receiver list of client side elements (use buildListOfElements or listOfElements) or client side operation that returns array
     * @param prefix character sequence put at the start of the joint collection elements
     * @param postfix character sequence put at the end of the joint collection elements
     * @param limit number of elements before truncated string
     * @param truncated replaces rest of the elements when the limit is reached
     * @return joinToString operator, evaluated client side.
     * Operator returns a string from all the elements
     * @see: JoinToStringOperationTest
     */
    @ClientLogicMarker
    fun <T : ClientLogicElement> ClientLogicArray<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        limit: Int = -1,
        truncated: String = "..."
    ) = JoinToStringOperatorFactory().create(this, JoinToStringArguments(separator, prefix, postfix, limit, truncated))
}

private data class JoinToStringArguments(
    val separator: String,
    val prefix: String,
    val postfix: String,
    val limit: Int,
    val truncated: String
)

private class JoinToStringOperatorFactory {
    fun create(
        element: ClientLogicElement,
        arguments: JoinToStringArguments
    ) = with(arguments) {
        ClientLogicOperator.Builder("joinToString")
            .add(element)
            .add(StringElement(separator))
            .add(StringElement(prefix))
            .add(StringElement(postfix))
            .add(NumberElement(limit))
            .add(StringElement(truncated))
            .build()
    }
}

