package com.demo.lang;

import org.junit.Test;

/**
 * @author developer.wang
 * @date 2020/3/14
 */
public class CodeTypeTest {

    @Test
    public void test() {
        System.out.println(CodeType.REGISTER);
        System.out.println(CodeType.REGISTER.toString());
        System.out.println(CodeType.REGISTER.name());
        System.out.println(CodeType.REGISTER.ordinal());
        System.out.println(CodeType.REGISTER.getDeclaringClass());
    }
}
