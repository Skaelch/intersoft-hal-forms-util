package de.intersoft.hal.forms;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(NON_ABSENT)
public class Property {

    @JsonProperty
    private String  name;

    @JsonProperty
    private String  prompt;

    @JsonProperty
    private Boolean readOnly;

    @JsonProperty
    private String  regex;

    @JsonProperty
    private Boolean required;

    @JsonProperty
    private Boolean templated;

    @JsonProperty
    private Object  value;

    @JsonIgnore
    private Map<String, Object> extra;
    
    @JsonProperty
    private Suggest suggest;

    private Property(
        String              name,
        String              prompt,
        Boolean             readOnly,
        String              regex,
        Boolean             required,
        Boolean             templated,
        Object              value,
        Suggest suggest,
        Map<String, Object> extra)
    {
        this.name      = requireNonNull(name);
        this.prompt    = prompt;
        this.readOnly  = (readOnly == null) ? null : (readOnly ? true : null);
        this.regex     = regex;
        this.required  = (required == null) ? null : (required ? true : null);
        this.templated = templated;
        this.value     = value;
        this.suggest   = suggest;
        this.extra     = (extra == null) ? emptyMap() : new HashMap<>(extra);
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getPrompt() {
        return prompt;
    }

    @JsonIgnore
    public boolean isReadOnly() {
        return (readOnly == null) ? false : readOnly;
    }

    @JsonIgnore
    public String getRegex() {
        return regex;
    }

    @JsonIgnore
    public boolean isRequired() {
        return (required == null) ? false : required;
    }

    @JsonIgnore
    public boolean getTemplated() {
        return (templated == null) ? false : templated;
    }

    @JsonIgnore
    public Object getValue() {
        return value;
    }

    @JsonAnyGetter
    public Map<String, Object> getExtra() {
        return this.extra;
    }

    @JsonAnySetter
    protected void putExtra(String key, Object value) {
        this.extra.put(key, value);
    }

    public static Property property(String name) {
        return new Property(name, null, null, null, null, null, null, null, emptyMap());
    }

    public static Property requiredProperty(String name, String prompt) {
        return new Property(name, prompt, null, null, true, null, null, null, emptyMap());
    }

    public static Builder propertyBuilder(String name) {
        return new Builder(name);
    }

    public static Builder copyOf(final Property property) {
        return new Builder(property.name)
            .withPrompt(property.prompt)
            .withReadOnly(property.readOnly)
            .withRegex(property.regex)
            .withRequired(property.required)
            .withTemplated(property.templated)
            .withValue(property.value)
            .withSuggest(property.suggest)
            .with(property.extra);
    }

    public static class Builder {

        private String              name;
        private String              prompt;
        private Boolean             readOnly;
        private String              regex;
        private Boolean             required;
        private Boolean             templated;
        private Object              value;
        private Suggest suggest;
        private Map<String, Object> extra;

        public Builder(String name) {
            this.name  = requireNonNull(name);
            this.extra = new HashMap<>();
        }

        public Property build() {
            return new Property(name, prompt, readOnly, regex, required, templated, value, suggest, extra);
        }

        public Builder withPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder withReadOnly(Boolean readOnly) {
            this.readOnly = readOnly;
            return this;
        }

        public Builder withRegex(String regex) {
            this.regex = regex;
            return this;
        }

        public Builder withRequired(Boolean required) {
            this.required = required;
            return this;
        }

        public Builder withTemplated(Boolean templated) {
            this.templated = templated;
            return this;
        }

        public Builder withValue(Object value) {
            this.value = value;
            return this;
        }
        
        public Builder withSuggest(Suggest suggest) {
            this.suggest = suggest;
            return this;
        }

        public Builder with(String key, Object value) {
            if (value != null) {
                this.extra.put(key, value);
            }
            return this;
        }

        public Builder with(Map<String, Object> extra) {
            this.extra.putAll(extra);
            return this;
        }
    }
}
