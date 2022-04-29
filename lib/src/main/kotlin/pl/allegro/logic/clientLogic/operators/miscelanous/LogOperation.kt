package pl.allegro.logic.clientLogic.operators.miscelanous

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.operators.OperatorFactory

internal interface LogOperation {
    @ClientLogicMarker
    fun log(element: ClientLogicElement) = LogOperatorFactory().create(element)
}

private class LogOperatorFactory : OperatorFactory("log")
