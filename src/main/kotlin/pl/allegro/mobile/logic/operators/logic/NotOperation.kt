package pl.allegro.mobile.logic.operators.logic

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.ClientLogicMarker

internal interface NotOperation {
    @ClientLogicMarker
    fun ClientLogicElement.not() = NotOperatorFactory().create(this)
}

private class NotOperatorFactory {

    fun create(element: ClientLogicElement): ClientLogicElement {
        return if (element is ClientLogicOperator && element.name == NOT_OPERATOR_NAME) {
            DoubleNotOperatorFactory().create(element.arguments.first())
        } else {
            ClientLogicOperator.Builder(NOT_OPERATOR_NAME).add(element).build()
        }
    }

    companion object {
        private const val NOT_OPERATOR_NAME = "!"
    }
}
