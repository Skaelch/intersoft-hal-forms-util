package de.intersoft.hal.forms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;


public class TemplatesTest {
    @Test
    public void emptyTemplateList() {
        Templates templates = Templates.withTemplates(new ArrayList<>());

        assertTrue(templates.isEmpty());
    }

    private Template createTemplate(String name) {
        return Template.defaultTemplate(name, Collections.singletonList(Property.property("bla")));
    }
}
