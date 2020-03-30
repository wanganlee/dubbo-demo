package com.demo.spi;

/**
 * @author developer.wang
 * @date 2020/2/26
 */
public class Bumblebee implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
