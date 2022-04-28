package pl.allegro.logic.clientLogic.operators.miscelanous

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface LogOperation {
    @ClientLogicMarker
    fun log(element: ClientLogicElement) = LogOperatorFactory().create(element)
}

private class LogOperatorFactory : OperatorFactory("log")
