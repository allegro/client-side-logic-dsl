package pl.allegro.logic

import pl.allegro.logic.operators.numeric.GreaterThanOperation
import pl.allegro.logic.operators.arithmetic.AdditionOperation
import pl.allegro.logic.operators.arithmetic.DivisionOperation
import pl.allegro.logic.operators.arithmetic.ModuloOperation
import pl.allegro.logic.operators.arithmetic.MultiplicationOperation
import pl.allegro.logic.operators.arithmetic.SubtractionOperation
import pl.allegro.logic.operators.array.AllOperation
import pl.allegro.logic.operators.array.FilterOperation
import pl.allegro.logic.operators.array.InOperation
import pl.allegro.logic.operators.array.MapOperation
import pl.allegro.logic.operators.array.MergeOperation
import pl.allegro.logic.operators.array.NoneOperation
import pl.allegro.logic.operators.array.ReduceOperation
import pl.allegro.logic.operators.array.SomeOperation
import pl.allegro.logic.operators.dataaccess.MissingOperation
import pl.allegro.logic.operators.dataaccess.MissingSomeOperation
import pl.allegro.logic.operators.dataaccess.PrimitiveToElementConversion
import pl.allegro.logic.operators.logic.AndOperation
import pl.allegro.logic.operators.logic.DoubleNotOperation
import pl.allegro.logic.operators.logic.EqualOperation
import pl.allegro.logic.operators.logic.IfOperation
import pl.allegro.logic.operators.logic.NotEqualOperation
import pl.allegro.logic.operators.logic.NotOperation
import pl.allegro.logic.operators.logic.OrOperation
import pl.allegro.logic.operators.logic.StrictEqualOperation
import pl.allegro.logic.operators.logic.StrictNotEqualOperation
import pl.allegro.logic.operators.miscelanous.LogOperation
import pl.allegro.logic.operators.numeric.BetweenOperation
import pl.allegro.logic.operators.numeric.BetweenOrEqualOperation
import pl.allegro.logic.operators.numeric.GreaterOrEqualThanOperation
import pl.allegro.logic.operators.numeric.LessOrEqualThanOperation
import pl.allegro.logic.operators.numeric.LessThanOperation
import pl.allegro.logic.operators.numeric.MaxOperation
import pl.allegro.logic.operators.numeric.MinOperation
import pl.allegro.logic.operators.string.ConcatenateOperation
import pl.allegro.logic.operators.string.ContainsStringOperation
import pl.allegro.logic.operators.string.SubstringOperation

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
