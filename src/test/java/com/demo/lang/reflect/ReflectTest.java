package com.demo.lang.reflect;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author developer.wang
 * @date 2020/3/20
 */
public class ReflectTest {

    @Test
    public void reflect() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        // 获取TargetObject类的Class对象，并且创建TargetClass实例
        // Class<TargetObject> tClass = TargetObject.class;
        // System.out.println(tClass);
        // System.out.println(tClass.newInstance());

        Class<?> aClass = Class.forName("com.demo.lang.reflect.TargetObject");
        System.out.println(aClass);
        System.out.println(aClass.newInstance());


        // 获取类中定义的所有方法
        // Method[] declaredMethods = aClass.getDeclaredMethods();
        // System.out.println("declaredMethods:");
        // for (Method method : declaredMethods) {
        //     System.out.println(method.getName());
        // }
        //
        // System.out.println("methods:");
        // for (Method method : aClass.getMethods()) {
        //     System.out.println(method.getName());
        // }

        try {
            // 获取指定方法并调用
            Method method = aClass.getMethod("publicMethod", String.class);
            aClass.getMethod("publicMethod", String.class, Integer.class);
            Object result = method.invoke(aClass.newInstance(), "java");
            // null will be returned if the method return type is void.
            // System.out.println(result);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            // 获取指定参数并对参数进行修改
            Object object = aClass.newInstance();
            Field field = aClass.getDeclaredField("value");
            // System.out.println(String.format("the field 'value' is accessible? %s", field.isAccessible()));
            // ReflectionUtils.makeAccessible(field);
            //
            // Object fileValue = field.get(object);
            // System.out.println(String.format("the field 'value' is: %s", fileValue));
            //
            //
            // System.out.println(String.format("the field 'value' is accessible? %s", field.isAccessible()));
            // field.set(object, "big data");
            // System.out.println(String.format("the field 'value' changed is: %s", field.get(object)));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            // 调用private方法
            Method privateMethod = aClass.getDeclaredMethod("privateMethod");
            System.out.println(String.format("private method is accessible ? %s", privateMethod.isAccessible()));

            ReflectionUtils.makeAccessible(privateMethod);
            System.out.println(String.format("private method is accessible ? %s", privateMethod.isAccessible()));
            privateMethod.invoke(aClass.newInstance());


        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
