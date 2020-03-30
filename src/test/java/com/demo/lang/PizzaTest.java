package com.demo.lang;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.EnumMap;
import java.util.List;

/**
 * @author developer.wang
 * @date 2020/3/14
 */
public class PizzaTest {

    private List<Pizza> pizzas;

    @Before
    public void before() {
        this.pizzas = Lists.newArrayList();
        this.pizzas.add(Pizza.newBuilder().pizzaStatus(Pizza.PizzaStatus.ORDERED).build());
        this.pizzas.add(Pizza.newBuilder().pizzaStatus(Pizza.PizzaStatus.ORDERED).build());
        this.pizzas.add(Pizza.newBuilder().pizzaStatus(Pizza.PizzaStatus.READY).build());
        this.pizzas.add(Pizza.newBuilder().pizzaStatus(Pizza.PizzaStatus.DELIVERED).build());
    }

    @Test
    public void testEnum() {
        Pizza pizza = Pizza.newBuilder().pizzaStatus(Pizza.PizzaStatus.ORDERED).build();
        // Pizza pizza = Pizza.newBuilder().pizzaStatus(Pizza.PizzaStatus.DELIVERED).build();
        Assert.assertTrue(pizza.isDeliverable());
    }

    @Test
    public void testEnumSet() {
        List<Pizza> results = Pizza.getAllUndeliverablePizzas(this.pizzas);
        Assert.assertEquals(results.size(), 3);
    }

    @Test
    public void testEnumMap() {
        EnumMap<Pizza.PizzaStatus, List<Pizza>> map = Pizza.groupPizzaByStatus(this.pizzas);
        map.entrySet().forEach(System.out::println);
    }
}
