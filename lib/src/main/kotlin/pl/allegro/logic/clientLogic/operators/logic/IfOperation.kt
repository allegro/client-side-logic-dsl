@file:OptIn(ExperimentalTypeInference::class)
@file:Suppress("INAPPLICABLE_JVM_NAME", "FunctionName")

package pl.allegro.logic.clientLogic.operators.logic

import pl.allegro.logic.clientLogic.ClientLogicElement
import pl.allegro.logic.clientLogic.ClientLogicMarker
import pl.allegro.logic.clientLogic.ClientLogicOperator
import pl.allegro.logic.clientLogic.BooleanElement
import pl.allegro.logic.clientLogic.ClientLogicArray
import pl.allegro.logic.clientLogic.NumberElement
import pl.allegro.logic.clientLogic.StringElement
import kotlin.experimental.ExperimentalTypeInference

internal interface IfOperation {
    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    fun If(condition: ClientLogicElement, thenBranch: () -> ClientLogicElement): IfOperatorBuilder =
        IfOperatorBuilder(condition, thenBranch)

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("ifString")
    fun If(condition: ClientLogicElement, thenBranch: () -> String): IfOperatorBuilder =
        IfOperatorBuilder(condition) { StringElement(thenBranch()) }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("ifNumber")
    fun If(condition: ClientLogicElement, thenBranch: () -> Number): IfOperatorBuilder =
        IfOperatorBuilder(condition) { NumberElement(thenBranch()) }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("ifBoolean")
    fun If(condition: ClientLogicElement, thenBranch: () -> Boolean): IfOperatorBuilder =
        IfOperatorBuilder(condition) { BooleanElement(thenBranch()) }

    @ClientLogicMarker
    fun ClientLogicElement.isEmpty() = isFalsy()

    @ClientLogicMarker
    fun ClientLogicElement.isNotEmpty() = isTruthy()

    @ClientLogicMarker
    fun ClientLogicElement.isTruthy() =
        If(this) { BooleanElement(true) }.Else { BooleanElement(false) }

    @ClientLogicMarker
    fun ClientLogicElement.isFalsy() =
        If(this) { BooleanElement(false) }.Else { BooleanElement(true) }
}

class IfOperatorBuilder(
    condition: ClientLogicElement,
    thenBranch: () -> ClientLogicElement
) {

    private val builder = ClientLogicOperator.Builder("if")

    init {
        builder.add(condition)
        builder.add(thenBranch())
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    fun ElseIf(condition: ClientLogicElement, thenBranch: () -> ClientLogicElement) = apply {
        builder.add(condition)
        builder.add(thenBranch())
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("elseIfString")
    fun ElseIf(condition: ClientLogicElement, thenBranch: () -> String) = apply {
        builder.add(condition)
        builder.add(StringElement(thenBranch()))
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("elseIfNumber")
    fun ElseIf(condition: ClientLogicElement, thenBranch: () -> Number) = apply {
        builder.add(condition)
        builder.add(NumberElement(thenBranch()))
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("elseIfBoolean")
    fun ElseIf(condition: ClientLogicElement, thenBranch: () -> Boolean) = apply {
        builder.add(condition)
        builder.add(BooleanElement(thenBranch()))
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    fun Else(defaultBranch: () -> ClientLogicElement): ClientLogicArray<ClientLogicElement> {
        builder.add(defaultBranch())
        return builder.build()
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("elseString")
    fun Else(defaultBranch: () -> String): ClientLogicElement {
        builder.add(StringElement(defaultBranch()))
        return builder.build()
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("elseNumber")
    fun Else(defaultBranch: () -> Number): ClientLogicElement {
        builder.add(NumberElement(defaultBranch()))
        return builder.build()
    }

    @ClientLogicMarker
    @OverloadResolutionByLambdaReturnType
    @JvmName("elseBoolean")
    fun Else(defaultBranch: () -> Boolean): ClientLogicElement {
        builder.add(BooleanElement(defaultBranch()))
        return builder.build()
    }
}
