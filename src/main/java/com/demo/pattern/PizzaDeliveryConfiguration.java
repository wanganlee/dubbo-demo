package com.demo.pattern;

/**
 * 通过枚举实现一些设计模式.
 *
 * @author developer.wang
 * @date 2020/3/14
 */
public enum PizzaDeliveryConfiguration {

    INSTANCE;

    PizzaDeliveryConfiguration() {
        // initialize configuration which involves overriding default methods.
    }

    private PizzaDeliveryStrategy deliveryStrategy = PizzaDeliveryStrategy.NORMAL;

    public static PizzaDeliveryConfiguration getInstance() {
        return INSTANCE;
    }

    public PizzaDeliveryStrategy getDeliveryStrategy() {
        return deliveryStrategy;
    }
}
