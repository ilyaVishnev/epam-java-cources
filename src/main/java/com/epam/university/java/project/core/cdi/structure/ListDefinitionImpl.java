package com.epam.university.java.project.core.cdi.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListDefinitionImpl implements ListDefinition {

    private List<ListItemDefinition> items = new ArrayList<>();

    @Override
    public Collection<ListItemDefinition> getItems() {
        return items;
    }

    @Override
    public void setItems(Collection<ListItemDefinition> items) {
        this.items = new ArrayList<>(items);
    }

    public static class ListItemDefinitionImpl implements ListItemDefinition {

        private String value;

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public void setValue(String value) {
            this.value = value;
        }
    }
}
