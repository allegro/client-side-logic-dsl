package pl.allegro.logic

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule

class ClientLogicModule : SimpleModule() {

    override fun setupModule(context: SetupContext) {
        addSerializer(StringElement::class.java, TextSerializer())
        addSerializer(BooleanElement::class.java, BooleanSerializer())
        addSerializer(NumberElement::class.java, NumberSerializer())
        addSerializer(ListOfClientElements::class.java, ListOfElementsSerializer())
        addSerializer(ClientLogicOperator::class.java, OperatorSerializer())
        addSerializer(NullElement::class.java, NullElementSerializer())
        super.setupModule(context)
    }

    override fun getModuleName() = "jsonLogic"
}

internal class OperatorSerializer : JsonSerializer<ClientLogicOperator>() {
    override fun serialize(value: ClientLogicOperator?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            if (value.arguments.size == 1) {
                gen.writeObject(mapOf(value.name to value.arguments.first()))
            } else {
                gen.writeObject(mapOf(value.name to value.arguments))
            }
        }
    }
}

internal class TextSerializer : JsonSerializer<StringElement>() {
    override fun serialize(value: StringElement?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            gen.writeString(value.value)
        }
    }
}

internal class BooleanSerializer : JsonSerializer<BooleanElement>() {
    override fun serialize(value: BooleanElement?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            gen.writeBoolean(value.value)
        }
    }
}

internal class NumberSerializer : JsonSerializer<NumberElement>() {
    override fun serialize(value: NumberElement?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            gen.writeNumber(value.value)
        }
    }
}

class ListOfElementsSerializer : JsonSerializer<ListOfClientElements<*>>() {
    override fun serialize(value: ListOfClientElements<*>?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            gen.writeObject(value.values)
        }
    }
}

internal class NullElementSerializer : JsonSerializer<NullElement>() {
    override fun serialize(value: NullElement?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            gen.writeNull()
        }
    }
}
