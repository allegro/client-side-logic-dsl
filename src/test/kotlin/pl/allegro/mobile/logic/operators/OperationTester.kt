package pl.allegro.mobile.logic.operators

import com.fasterxml.jackson.databind.json.JsonMapper
import org.assertj.core.api.Assertions.assertThat
import org.intellij.lang.annotations.Language
import org.junit.jupiter.params.provider.Arguments
import pl.allegro.mobile.logic.ClientLogicElement
import pl.allegro.mobile.logic.ClientLogicModule
import java.util.stream.Stream

fun ClientLogicElement.isEqualAfterSerialization(expected: String) {
    val expressionJson = ClientLogicToJsonMapper.clientLogicExpressionToJson(this).stripWhitespace()
    assertThat(expressionJson).isEqualTo(expected.stripWhitespace())
}

class JsonLogicTestData(
    val testCase: String = "",
    val expression: ClientLogicElement,
    @Language("JSON") val expected: String
)

fun List<JsonLogicTestData>.toJsonLogicTestArgumentsStream(): Stream<Arguments?>? =
    Stream.of(*(this.map { Arguments.of(it.testCase, it) }.toTypedArray()))

fun JsonLogicTestData.assertSerializedExpressionMatchesExpected(stripWhitespaces: Boolean = true) {
    val expressionJson = ClientLogicToJsonMapper.clientLogicExpressionToJson(expression).let { resultJson ->
        if (stripWhitespaces) resultJson.stripWhitespace() else resultJson
    }

    val expectedJson = if (stripWhitespaces) expected.stripWhitespace() else expected

    assertThat(expressionJson).isEqualTo(expectedJson)
}

private object ClientLogicToJsonMapper {

    private val jsonMapper = JsonMapper.builder()
        .addModule(ClientLogicModule())
        .build()

    fun clientLogicExpressionToJson(element: ClientLogicElement): String =
        jsonMapper.writeValueAsString(element)
}

private fun String.stripWhitespace(): String {
    return this
        .replace(" ", "")
        .replace("\n", "")
        .replace("\t", "")
}
