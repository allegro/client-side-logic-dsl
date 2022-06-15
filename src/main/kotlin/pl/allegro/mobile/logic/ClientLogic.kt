package pl.allegro.mobile.logic

import pl.allegro.mobile.logic.operators.numeric.GreaterThanOperation
import pl.allegro.mobile.logic.operators.arithmetic.AdditionOperation
import pl.allegro.mobile.logic.operators.arithmetic.DivisionOperation
import pl.allegro.mobile.logic.operators.arithmetic.ModuloOperation
import pl.allegro.mobile.logic.operators.arithmetic.MultiplicationOperation
import pl.allegro.mobile.logic.operators.arithmetic.SubtractionOperation
import pl.allegro.mobile.logic.operators.array.AllOperation
import pl.allegro.mobile.logic.operators.array.FilterOperation
import pl.allegro.mobile.logic.operators.array.InOperation
import pl.allegro.mobile.logic.operators.array.MapOperation
import pl.allegro.mobile.logic.operators.array.MergeOperation
import pl.allegro.mobile.logic.operators.array.NoneOperation
import pl.allegro.mobile.logic.operators.array.ReduceOperation
import pl.allegro.mobile.logic.operators.array.SomeOperation
import pl.allegro.mobile.logic.operators.dataaccess.MissingOperation
import pl.allegro.mobile.logic.operators.dataaccess.MissingSomeOperation
import pl.allegro.mobile.logic.operators.dataaccess.PrimitiveToElementConversion
import pl.allegro.mobile.logic.operators.logic.AndOperation
import pl.allegro.mobile.logic.operators.logic.DoubleNotOperation
import pl.allegro.mobile.logic.operators.logic.EqualOperation
import pl.allegro.mobile.logic.operators.logic.IfOperation
import pl.allegro.mobile.logic.operators.logic.NotEqualOperation
import pl.allegro.mobile.logic.operators.logic.NotOperation
import pl.allegro.mobile.logic.operators.logic.OrOperation
import pl.allegro.mobile.logic.operators.logic.StrictEqualOperation
import pl.allegro.mobile.logic.operators.logic.StrictNotEqualOperation
import pl.allegro.mobile.logic.operators.miscelanous.LogOperation
import pl.allegro.mobile.logic.operators.numeric.BetweenOperation
import pl.allegro.mobile.logic.operators.numeric.BetweenOrEqualOperation
import pl.allegro.mobile.logic.operators.numeric.GreaterOrEqualThanOperation
import pl.allegro.mobile.logic.operators.numeric.LessOrEqualThanOperation
import pl.allegro.mobile.logic.operators.numeric.LessThanOperation
import pl.allegro.mobile.logic.operators.numeric.MaxOperation
import pl.allegro.mobile.logic.operators.numeric.MinOperation
import pl.allegro.mobile.logic.operators.string.CapitalizeOperation
import pl.allegro.mobile.logic.operators.string.ConcatenateOperation
import pl.allegro.mobile.logic.operators.string.ContainsStringOperation
import pl.allegro.mobile.logic.operators.string.LowercaseOperation
import pl.allegro.mobile.logic.operators.string.SubstringOperation
import pl.allegro.mobile.logic.operators.string.UppercaseOperation

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
    LowercaseOperation,
    UppercaseOperation,
    CapitalizeOperation,

    // misc
    LogOperation
