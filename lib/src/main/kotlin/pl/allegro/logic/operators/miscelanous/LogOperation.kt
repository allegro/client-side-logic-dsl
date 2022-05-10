package pl.allegro.logic.operators.miscelanous

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicMarker
import pl.allegro.logic.operators.OperatorFactory

internal interface LogOperation {
    @ClientLogicMarker
    fun log(element: ClientLogicElement) = LogOperatorFactory().create(element)
}

private class LogOperatorFactory : OperatorFactory("log")
