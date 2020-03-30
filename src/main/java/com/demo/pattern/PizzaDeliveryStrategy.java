package com.demo.pattern;

import com.demo.lang.Pizza;

/**
 * 使用枚举实现策略模式.
 *
 * @author developer.wang
 * @date 2020/3/14
 */
public enum PizzaDeliveryStrategy {

    EXPRESS {
        @Override
        public void deliver(Pizza pizza) {
            System.out.println("Pizza will be delivered in express mode");
        }
    },
    NORMAL {
        @Override
        public void deliver(Pizza pizza) {
            System.out.println("Pizza will be delivered in normal mode");
        }
    };


    public abstract void deliver(Pizza pizza);
}
