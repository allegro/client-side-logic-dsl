package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.ClientLogicOperator

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
