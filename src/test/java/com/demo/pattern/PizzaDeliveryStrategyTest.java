package com.demo.pattern;

import com.demo.lang.Pizza;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author developer.wang
 * @date 2020/3/14
 */
public class PizzaDeliveryStrategyTest {


    @Test
    public void testStrategy() throws IllegalAccessException {
        Pizza pizza = Pizza.newBuilder().pizzaStatus(Pizza.PizzaStatus.DELIVERED).build();
        pizza.deliver();

        Field[] fields = pizza.getClass().getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return;
        }
        Field pizzaStatus = Arrays.stream(fields).filter(Objects::nonNull).filter(o -> Pizza.PizzaStatus.class.equals(o.getType())).findFirst().orElse(null);
        if (Objects.isNull(pizzaStatus)) {
            return;
        }
        ReflectionUtils.makeAccessible(pizzaStatus);
        System.out.println(String.format("field is enum constant ? : %s", pizzaStatus.isEnumConstant()));
        System.out.println(String.format("field is : %s", pizzaStatus.get(pizza)));


    }
}
