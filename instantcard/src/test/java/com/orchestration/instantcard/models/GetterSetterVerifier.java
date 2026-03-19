package com.orchestration.instantcard.models;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Assertions;

import com.google.common.base.Defaults;

import jakarta.annotation.Nonnull;

public class GetterSetterVerifier <T>{

	private Class<T> type;


    private GetterSetterVerifier(@Nonnull final Class<T> type) {
        this.type = type;
    }

    public static <T> GetterSetterVerifier<T> forClass(@Nonnull final Class<T> type) {
        return new GetterSetterVerifier<>(type);
    }

    public void verify() {
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(type);
            final PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();

            for (final PropertyDescriptor property : properties) {
                if (shouldTestProperty(property)) {
                    testProperty(property);
                }
            }
        } catch (final Exception e) {
            throw new AssertionError(e.getMessage());
        }
    }

    private boolean shouldTestProperty(@Nonnull final PropertyDescriptor property) {
        if (property.getWriteMethod() == null || property.getReadMethod() == null) {
            return false;
        } return true;
    }

    private void testProperty(@Nonnull final PropertyDescriptor property)
            throws IllegalAccessException, InstantiationException, InvocationTargetException, IllegalArgumentException,
            NoSuchMethodException, SecurityException {
        final Object target = type.getDeclaredConstructor().newInstance();
        final Object setValue = Defaults.defaultValue(property.getPropertyType());

        final Method getter = property.getReadMethod();
        final Method setter = property.getWriteMethod();

        setter.invoke(target, setValue);
        final Object getValue = getter.invoke(target);

        Assertions.assertEquals(setValue, getValue,
            property.getDisplayName() + " getter / setter do not produce the same result.");
    }
    
}

