package com.epam.university.java.core.task041;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalInt;

public class Task041Impl implements Task041 {

    @Override
    public Entity create(Collection<Entity> collection, String value) {
        if (collection == null || value == null) {
            throw new IllegalArgumentException();
        }
        int id = collection.stream().mapToInt(i -> i.getId()).max()
                .orElse(0);
        Entity entity = new EntityImpl(id == 0 ? 0 : id + 1, value);
        collection.add(entity);
        return entity;
    }

    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        if (!collection.contains(entity)) {
            throw new IllegalArgumentException();
        }
        return collection.stream().filter(e -> e.getId() == entity
                .getId()).findFirst().get();
    }

    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        if (collection == null || entity == null || value == null
                || !collection.contains(entity)) {
            throw new IllegalArgumentException();
        }
        Entity e = read(collection, entity);
        Field[] fields = e.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("value")) {
                try {
                    field.setAccessible(true);
                    field.set(e, value);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        if (!collection.contains(entity)) {
            throw new IllegalArgumentException();
        }
        collection.remove(entity);
    }
}
