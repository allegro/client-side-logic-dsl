package pl.allegro.logic.operators

import pl.allegro.logic.ClientLogicElement
import pl.allegro.logic.ClientLogicOperator
import pl.allegro.logic.ListOfClientElements

internal abstract class OperatorFactory(protected val name: String) {
    fun create(vararg elements: ClientLogicElement): ClientLogicElement =
        ClientLogicOperator.Builder(name).addAll(elements).build()

    fun create(elements: ListOfClientElements<out ClientLogicElement>) =
        ClientLogicOperator.Builder(name).addAll(elements.values).build()
}

// allows to flatten {"+" [1, {"+":[3, 4]}]} to {"+": [1, 3, 4]}
internal abstract class FlattenableOperatorFactory(protected val name: String) {
    fun create(vararg elements: ClientLogicElement): ClientLogicElement =
        ClientLogicOperator.Builder(name)
            .addAll(elements.map { flattenArgumentsIfTheSameOperator(it) }.flatten())
            .build()

    fun create(elements: List<ClientLogicElement>): ClientLogicElement =
        ClientLogicOperator.Builder(name)
            .addAll(elements.map { flattenArgumentsIfTheSameOperator(it) }.flatten())
            .build()

    private fun flattenArgumentsIfTheSameOperator(element: ClientLogicElement): List<ClientLogicElement> {
        return if (element is ClientLogicOperator && element.name == name) {
            element.arguments
        } else {
            listOf(element)
        }
    }
}
