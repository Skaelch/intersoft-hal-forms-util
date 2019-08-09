package hal.forms;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(NON_ABSENT)
public class Template {

    private static final String DEFAULT_TEMPLATE_KEY = "default";

    @JsonIgnore
    private String key;

    @JsonProperty
    private String title;

    @JsonProperty
    @JsonSerialize(using = MediaTypeSerializer.class)
    @JsonDeserialize(using = MediaTypeDeserializer.class)
    private String contentType;

    @JsonProperty
    private String method;

    @JsonProperty
    @JsonInclude(NON_EMPTY)
    private List<Property> properties;
    
    private Template(String key, String title, String contentType, String method, List<Property> properties) {
        this.key         = requireNonNull(key);
        this.title       = title;
        this.contentType = (contentType == null) ? "application/json" : contentType;
        this.method      = (method      == null) ? "GET" : method;
        this.properties  = (properties  == null) ? emptyList()                : new ArrayList<>(properties);
    }

    @JsonIgnore
    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getContentType() {
        return contentType;
    }

    public String getMethod() {
        return method;
    }

    public List<Property> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    public static Template defaultTemplate(String title, List<Property> properties) {
        return new Template(DEFAULT_TEMPLATE_KEY, title, null, null, properties);
    }

    public static Template defaultTemplate(String title, String contentType, String method, List<Property> properties) {
        return new Template(DEFAULT_TEMPLATE_KEY, title, contentType, method, properties);
    }
    
    public static class MediaTypeSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class MediaTypeDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return String.valueOf(p.getValueAsString());
        }
    }
}
