package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement

internal interface  CompareToDateOperation {
    /**
     * Compares a string to given string representing the date in a proper format.
     * This operation compares the date
     * @receiver Character sequence or client side operation that returns string
     * @param date date to compare to date represents in current string
     * @return integer interpretation of state. State is between -1 and 1.
     * -1 - passed date is AFTER the compared date
     * 0 - passed date is EQUALS the compared date
     * 1 - passed date is BEFORE the compared date
     */
    @ClientLogicMarker
    fun ClientLogicElement.compareToDate(date: String) =
        CompareToDateOperationFactory().create(this,date)

    /**
     * Compares a string to given string representing the date in a proper format.
     * This operation compares the date
     * @receiver Character sequence or client side operation that returns string
     * @param registryKey key represents stored date to compare
     * @return integer interpretation of state. State is between -1 and 1.
     * -1 - passed date is AFTER the compared date
     * 0 - passed date is EQUALS the compared date
     * 1 - passed date is BEFORE the compared date
     */
    @ClientLogicMarker
    fun ClientLogicElement.compareToDate(registryKey: ClientLogicElement) =
        CompareToDateOperationFactory().create(this, registryKey)
}

private class CompareToDateOperationFactory {
    fun create(
        element: ClientLogicElement,
        date: String
    ) = ClientLogicOperator.Builder("compareToDate")
        .add(element)
        .add(StringElement(date))
        .build()

    fun create(
        element: ClientLogicElement,
        date: ClientLogicElement
    ) = ClientLogicOperator.Builder("compareToDate")
        .add(element)
        .add(date)
        .build()

}
