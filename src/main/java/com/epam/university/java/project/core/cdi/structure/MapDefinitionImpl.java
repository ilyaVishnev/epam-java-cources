package com.epam.university.java.project.core.cdi.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MapDefinitionImpl implements MapDefinition {

    private List<MapEntryDefinition> values = new ArrayList<>();

    @Override
    public Collection<MapEntryDefinition> getValues() {
        return values;
    }

    @Override
    public void setValues(Collection<MapEntryDefinition> values) {
        this.values = new ArrayList<>(values);
    }

    public static class MapEntryDefinitionImpl implements MapEntryDefinition {

        private String key;
        private String value;
        private String ref;

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String getRef() {
            return this.ref;
        }

        @Override
        public void setRef(String reference) {
            this.ref = reference;
        }
    }
}
