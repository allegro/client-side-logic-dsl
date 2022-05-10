package pl.allegro.logic

@Suppress("EXPERIMENTAL_IS_NOT_ENABLED") //added because of warning even though our compiled adds required x-opt
@Target(
    AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION,
    AnnotationTarget.FUNCTION, AnnotationTarget.FIELD
)
@Retention(AnnotationRetention.RUNTIME)
@RequiresOptIn(message = "This Action is still work in progress. " +
    "Do not use unless you are testing client side logic.")
annotation class ClientLogicOptIn
