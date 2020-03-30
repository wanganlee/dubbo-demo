package com.demo.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * the reflect of the generic.
 *
 * @author developer.wang
 * @date 2020/3/24
 */
public class GenericObject<K, V> {

    static final class Node<K, V> {
        volatile V value;
        transient Node<K, V> next;
    }

    private String name;
    private int length;
    private List<Node> nodes;
    private Map<K, V> map;

    private List<Node> nodes() {
        return this.nodes;
    }

    private Map<K, V> map() {
        return this.map;
    }

    public static void main(String[] args) {
        Class<GenericObject> clazz = GenericObject.class;
        System.out.println(clazz);


        for (Field field : clazz.getDeclaredFields()) {
            Type type = field.getGenericType();
            // Type type = field.getType();
            if (type instanceof ParameterizedType) {
                System.out.println(String.format("field: %s is generic.", field.getName()));
                ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();

                System.out.println(String.format("field: %s rawType: %s, ActualType: %s", field.getName(), parameterizedType.getRawType(),
                        Arrays.toString(parameterizedType.getActualTypeArguments())));

            } else {
                System.out.println(String.format("field: %s ---> type: %s", field.getName(), type));
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            Type returnType = method.getGenericReturnType();
            // Type returnType = method.getReturnType();
            System.out.println(String.format("method: %s ---> type: %s", method.getName(), returnType));
            if (returnType instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
                System.out.println(String.format("method: %s is generic", method.getName()));
                System.out.println(String.format("method: %s rawType: %s, ActualType: %s", method.getName(), type.getRawType(),
                        Arrays.toString(type.getActualTypeArguments())));
            }
        }
    }
}
