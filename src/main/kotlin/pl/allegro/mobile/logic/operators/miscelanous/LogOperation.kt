package pl.allegro.mobile.logic.operators.miscelanous

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.operators.OperatorFactory

internal interface LogOperation {
    @ClientLogicMarker
    fun log(element: ClientLogicElement) = LogOperatorFactory().create(element)
}

private class LogOperatorFactory : OperatorFactory("log")
