package de.intersoft.hal.forms;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static de.otto.edison.hal.Curies.emptyCuries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.otto.edison.hal.Curies;
import de.otto.edison.hal.Embedded;
import de.otto.edison.hal.HalRepresentation;
import de.otto.edison.hal.Links;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class HalFormsRepresentation extends HalRepresentation {

    @JsonProperty("_templates")
    private volatile Templates templates;

    public HalFormsRepresentation() {
        this(null, null, null, emptyCuries());
    }

    public HalFormsRepresentation(Links links) {
        this(links, null, null, emptyCuries());
    }

    public HalFormsRepresentation(Links links, Templates templates) {
        this(links, null, templates, emptyCuries());
    }

    public HalFormsRepresentation(Links links, Curies curies) {
        this(links, null, null, curies);
    }

    public HalFormsRepresentation(Links links, Templates templates, Curies curies) {
        this(links, null, templates, curies);
    }

    public HalFormsRepresentation(Links links, Embedded embedded) {
        this(links, embedded, null, emptyCuries());
    }

    public HalFormsRepresentation(Links links, Embedded embedded, Templates templates) {
        this(links, embedded, templates, emptyCuries());
    }

    public HalFormsRepresentation(
        Links links,
        Embedded embedded,
        Curies curies)
    {
        this(links, embedded, null, curies);
    }

    public HalFormsRepresentation(
            Links links,
            Embedded embedded,
            Templates templates,
            Curies relRegistry)
    {
        super(links, embedded, relRegistry);
        this.templates = ((templates == null) || templates.isEmpty())
            ? null
            : templates;
    }

    @JsonIgnore
    public Templates getTemplates() {
        return templates;
    }
}
