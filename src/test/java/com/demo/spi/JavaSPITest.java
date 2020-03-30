package com.demo.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author developer.wang
 * @date 2020/2/26
 */
public class JavaSPITest {

    // @Test
    // public void testSayHello() {
    //     ServiceLoader<Robot> robots = ServiceLoader.load(Robot.class);
    //     System.out.println("Java SPI");
    //     robots.forEach(Robot::sayHello);
    // }

    public static void main(String[] args) {
        ServiceLoader<Robot> robots = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        robots.forEach(Robot::sayHello);
    }
}
