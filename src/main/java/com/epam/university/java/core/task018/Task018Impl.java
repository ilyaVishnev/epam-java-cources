package com.epam.university.java.core.task018;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Task018Impl implements Task018 {

    /**
     * Check is annotation present in the following object.
     *
     * @param toCheck          object to check
     * @param annotationToFind annotation to look for
     * @return is annotation presents
     */
    @Override
    public boolean isAnnotationPresent(Object toCheck, Class<?> annotationToFind) {
        if (toCheck == null || annotationToFind == null) {
            throw new IllegalArgumentException();
        }
        Class c = toCheck.getClass();
        boolean t;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(annotationToFind)) {
                    return true;
                }
            }
        }
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(annotationToFind)) {
                    return true;
                }
            }
        }
        Annotation[] annotationsCl = c.getAnnotations();
        for (Annotation annotation : annotationsCl) {
            if (annotation.annotationType().equals(annotationToFind)) {
                return true;
            }
        }
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            Annotation[] annotations = constructor.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(annotationToFind)) {
                    return true;
                }
            }
        }
        Method[] methods1 = c.getMethods();
        for (Method method : methods1) {
            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                for (int j = 0; j < annotations[0].length; j++) {
                    if (annotations[i][j].annotationType().equals(annotationToFind)) {
                        return true;
                    }
                }
            }
            Package myPackage = c.getPackage();
            Annotation[] annotationsP = myPackage.getAnnotations();
            for (Annotation annotation : annotationsP) {
                if (annotation.annotationType().equals(annotationToFind)) {
                    return true;
                }
            }
        }
        return false;
    }
}
