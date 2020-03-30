package com.demo.lang.reflect;

/**
 * @author developer.wang
 * @date 2020/3/20
 */
public class TargetObject {

    private String value;

    public TargetObject() {
        this.value = "JavaGuide";
    }

    public void publicMethod(String s) {
        System.out.println(String.format("I Love %s", s));
    }

    public void publicMethod(String s, Integer i) {
        System.out.println(String.format("I Love %s and %s", s, i));
    }

    private void privateMethod() {
        System.out.println(String.format("value is %s", this.value));
    }
}
