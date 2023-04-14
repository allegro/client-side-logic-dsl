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
     * @param precision precision to compare dates
     * @return integer interpretation of state. State is between -1 and 1.
     * -1 - passed date is AFTER the compared date
     * 0 - passed date is EQUALS the compared date
     * 1 - passed date is BEFORE the compared date
     */
    @ClientLogicMarker
    fun ClientLogicElement.compareToDate(date: String, precision: ComparePrecision) =
        CompareToDateOperationFactory().create(this, date, precision)

    /**
     * Compares a string to given string representing the date in a proper format.
     * This operation compares the date
     * @receiver Character sequence or client side operation that returns string
     * @param element key represents client logic element to compare
     * @param precision precision to compare dates
     * @return integer interpretation of state. State is between -1 and 1.
     * -1 - passed date is AFTER the compared date
     * 0 - passed date is EQUALS the compared date
     * 1 - passed date is BEFORE the compared date
     */
    @ClientLogicMarker
    fun ClientLogicElement.compareToDate(element: ClientLogicElement, precision: ComparePrecision) =
        CompareToDateOperationFactory().create(this, element, precision)
}

enum class ComparePrecision {
    MILLISECOND,SECOND,MINUTE,HOUR,DAY,MONTH,YEAR
}
private class CompareToDateOperationFactory {
    fun create(
        element: ClientLogicElement,
        date: String,
        precision: ComparePrecision
    ) = ClientLogicOperator.Builder("compareToDate")
        .add(element)
        .add(StringElement(date))
        .add(StringElement(precision.name))
        .build()

    fun create(
        element: ClientLogicElement,
        otherElement: ClientLogicElement,
        precision: ComparePrecision
    ) = ClientLogicOperator.Builder("compareToDate")
        .add(element)
        .add(otherElement)
        .add(StringElement(precision.name))
        .build()
}
