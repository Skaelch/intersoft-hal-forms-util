package de.intersoft.hal.forms;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = Suggest.SuggestSerializer.class)
public class Suggest {

    private final String      href;
    private final String      embedded;
    private final String      valueField;
    private final String      promptField;
    private final List<Value> values;

    private Suggest(String href, String embedded, String valueField, String promptField, List<Value> values) {
        this.href        = href;
        this.embedded    = embedded;
        this.valueField  = valueField;
        this.promptField = promptField;
        this.values      = values;
    }

    public static Suggest directSuggest(List<Value> values) {
        return new Suggest(null, null, null, null, requireNonNull(values));
    }

    public static Value value(Object value, String prompt) {
        return new Value(value, prompt);
    }
    
    public static Suggest embeddedSuggest(String embedded, String valueField, String promptField) {
        return new Suggest(null, requireNonNull(embedded), requireNonNull(valueField), requireNonNull(promptField), null);
    }

    public static Suggest remoteSuggest(String href, String embedded, String valueField, String promptField) {
        return new Suggest(requireNonNull(href), embedded, requireNonNull(valueField), requireNonNull(promptField), null);
    }
    
    public static class Value {
        private final Object value;
        private final String prompt;

        public Value(Object value, String prompt) {
            this.value  = requireNonNull(value);
            this.prompt = requireNonNull(prompt);
        }

        public Object getValue() {
            return value;
        }

        public String getPrompt() {
            return prompt;
        }
    }

    static class SuggestSerializer extends JsonSerializer<Suggest> {
        @Override
        public void serialize(Suggest suggest, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (suggest.values == null) {
                gen.writeStartObject();
                if (suggest.href != null) {
                    gen.writeStringField("href", suggest.href);
                }
                if (suggest.embedded != null) {
                    gen.writeStringField("embedded", suggest.embedded);
                }
                gen.writeStringField("prompt-field", suggest.promptField);
                gen.writeStringField("value-field",  suggest.valueField);
                gen.writeEndObject();
            } else {
                gen.writeStartArray();
                for (Value value : suggest.values) {
                    gen.writeObject(value);
                }
                gen.writeEndArray();
            }
        }
    }
}
