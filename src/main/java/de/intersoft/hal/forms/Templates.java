package de.intersoft.hal.forms;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = Templates.TemplatesSerializer.class)
public class Templates {

    private final Map<String, Template> templates = new LinkedHashMap<>();

    private Templates(Map<String, Template> templates) {
        this.templates.putAll(templates);
    }

    public boolean isEmpty() {
        return this.templates.isEmpty();
    }

    public static Templates withTemplates(final Template template, final Template... more) {
        List<Template> templates = new ArrayList<>();
        templates.add(template);
        templates.addAll(asList(more));
        return withTemplates(templates);
    }

    public static Templates withTemplates(final List<Template> templates) {
        Map<String, Template> templateMap = new HashMap<>();
        for (Template template : templates) {
            templateMap.putIfAbsent(template.getKey(), template);
        }
        return new Templates(templateMap);
    }

    public static class TemplatesSerializer extends JsonSerializer<Templates> {

        @Override
        public void serialize(Templates value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            for (Map.Entry<String, Template> entry : value.templates.entrySet()) {
                gen.writeObjectField(entry.getKey(), entry.getValue());
            }
            gen.writeEndObject();
        }
    }
}
