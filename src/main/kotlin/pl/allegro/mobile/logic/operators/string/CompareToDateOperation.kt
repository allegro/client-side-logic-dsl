package pl.allegro.mobile.logic.operators.string

import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicMarker
import pl.allegro.mobile.logic.ClientLogicOperator
import pl.allegro.mobile.logic.StringElement


internal interface  CompareToDateOperation {
    /**
     * Compares a string to given string represents the date in proper format.
     * This operation compares the date
     * @receiver Character sequence or client side operation that returns string
     * @param date date to compare to date represents in current string
     * @return integer interpretation of state. State is between -1 and 1.
     * -1 - passing date is BEFORE the compared date
     * 0 - passing date is EQUALS the compared date
     * 1 - passing date is AFTER the compared date
     */
    @ClientLogicMarker
    fun ClientLogicElement.compareToDate(date: String) =
        CompareToDateOperationFactory().create(this,date)
}

private class CompareToDateOperationFactory {
    fun create(
        element: ClientLogicElement,
        date: String
    ) = ClientLogicOperator.Builder("compareToDate")
        .add(element)
        .add(StringElement(date))
        .build()
}