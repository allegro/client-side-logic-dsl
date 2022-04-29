package pl.allegro.logic.clientLogic

import pl.allegro.logic.clientLogic.annotations.ClientLogicMarker
import pl.allegro.logic.clientLogic.annotations.ClientLogicOptIn
import pl.allegro.logic.clientLogic.operators.arithmetic.DivisionOperation
import pl.allegro.logic.clientLogic.operators.arithmetic.SubtractionOperation
import pl.allegro.logic.clientLogic.operators.arithmetic.ModuloOperation
import pl.allegro.logic.clientLogic.operators.arithmetic.MultiplicationOperation
import pl.allegro.logic.clientLogic.operators.arithmetic.AdditionOperation
import pl.allegro.logic.clientLogic.operators.array.AllOperation
import pl.allegro.logic.clientLogic.operators.array.FilterOperation
import pl.allegro.logic.clientLogic.operators.array.InOperation
import pl.allegro.logic.clientLogic.operators.array.MapOperation
import pl.allegro.logic.clientLogic.operators.array.MergeOperation
import pl.allegro.logic.clientLogic.operators.array.NoneOperation
import pl.allegro.logic.clientLogic.operators.array.ReduceOperation
import pl.allegro.logic.clientLogic.operators.array.SomeOperation
import pl.allegro.logic.clientLogic.operators.dataaccess.MissingOperation
import pl.allegro.logic.clientLogic.operators.dataaccess.MissingSomeOperation
import pl.allegro.logic.clientLogic.operators.dataaccess.PrimitiveToElementConversion
import pl.allegro.logic.clientLogic.operators.logic.AndOperation
import pl.allegro.logic.clientLogic.operators.logic.DoubleNotOperation
import pl.allegro.logic.clientLogic.operators.logic.EqualOperation
import pl.allegro.logic.clientLogic.operators.logic.IfOperation
import pl.allegro.logic.clientLogic.operators.logic.NotEqualOperation
import pl.allegro.logic.clientLogic.operators.logic.NotOperation
import pl.allegro.logic.clientLogic.operators.logic.OrOperation
import pl.allegro.logic.clientLogic.operators.logic.StrictEqualOperation
import pl.allegro.logic.clientLogic.operators.logic.StrictNotEqualOperation
import pl.allegro.logic.clientLogic.operators.miscelanous.LogOperation
import pl.allegro.logic.clientLogic.operators.numeric.BetweenOperation
import pl.allegro.logic.clientLogic.operators.numeric.BetweenOrEqualOperation
import pl.allegro.logic.clientLogic.operators.numeric.GreaterOrEqualThanOperation
import pl.allegro.logic.clientLogic.operators.numeric.GreaterThanOperation
import pl.allegro.logic.clientLogic.operators.numeric.LessOrEqualThanOperation
import pl.allegro.logic.clientLogic.operators.numeric.LessThanOperation
import pl.allegro.logic.clientLogic.operators.numeric.MaxOperation
import pl.allegro.logic.clientLogic.operators.numeric.MinOperation
import pl.allegro.logic.clientLogic.operators.string.ConcatenateOperation
import pl.allegro.logic.clientLogic.operators.string.ContainsStringOperation
import pl.allegro.logic.clientLogic.operators.string.SubstringOperation

@ClientLogicOptIn
@ClientLogicMarker
fun clientLogic(init: ClientLogic.() -> ClientLogicElement): ClientLogicElement {
    return ClientLogic.init()
}

object ClientLogic :
    PrimitiveToElementConversion,

    // arithmetic operations
    DivisionOperation,
    SubtractionOperation,
    ModuloOperation,
    MultiplicationOperation,
    AdditionOperation,

    // array operations
    AllOperation,
    FilterOperation,
    InOperation,
    MapOperation,
    MergeOperation,
    NoneOperation,
    ReduceOperation,
    SomeOperation,

    // data access
    MissingOperation,
    MissingSomeOperation,

    // logic
    AndOperation,
    DoubleNotOperation,
    EqualOperation,
    IfOperation,
    NotEqualOperation,
    NotOperation,
    OrOperation,
    StrictEqualOperation,
    StrictNotEqualOperation,

    // numeric
    BetweenOperation,
    BetweenOrEqualOperation,
    GreaterOrEqualThanOperation,
    GreaterThanOperation,
    LessOrEqualThanOperation,
    LessThanOperation,
    MaxOperation,
    MinOperation,

    // string
    ConcatenateOperation,
    ContainsStringOperation,
    SubstringOperation,

    // misc
    LogOperation
