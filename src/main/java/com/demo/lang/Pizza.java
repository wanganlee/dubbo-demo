package com.demo.lang;

import com.demo.pattern.PizzaDeliveryConfiguration;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.stream.Collectors;

/**
 * usage of the Enum.
 *
 * @author developer.wang
 * @date 2020/3/14
 */
public class Pizza {

    private PizzaStatus pizzaStatus;

    private static final EnumSet<PizzaStatus> UN_DELIVERED_PIZZA_STATUS = EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);

    public enum PizzaStatus {
        ORDERED(5) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },

        READY(2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },

        DELIVERED(0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }

        public int getTimeToDelivery() {
            return this.timeToDelivery;
        }

        PizzaStatus(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(PizzaStatus.class).add("name", this.name()).add("timeToDelivery", this.timeToDelivery).toString();
        }
    }


    private Pizza() {
    }

    private Pizza(PizzaStatus pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    // builder pattern
    public static final class Builder {
        private PizzaStatus pizzaStatus;

        public Builder pizzaStatus(PizzaStatus pizzaStatus) {
            this.pizzaStatus = pizzaStatus;
            return this;
        }

        public Pizza build() {
            return new Pizza(this.pizzaStatus);
        }
    }

    public boolean isDeliverable() {
        return this.pizzaStatus.isDelivered();
    }

    public void printTimeToDeliver() {
        System.out.println(String.format("Time to delivery is: %s", this.pizzaStatus.timeToDelivery));
    }

    public void deliver() {
        if (this.isDeliverable()) {
            PizzaDeliveryConfiguration.getInstance().getDeliveryStrategy().deliver(this);
        }
    }

    public PizzaStatus pizzaStatus() {
        return this.pizzaStatus;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Pizza.class).add("pizzaStatus", this.pizzaStatus).toString();
    }

    /**
     * EnumSet
     */
    public static List<Pizza> getAllUndeliverablePizzas(List<Pizza> pizzas) {
        return pizzas.stream().filter(Objects::nonNull).filter(o -> UN_DELIVERED_PIZZA_STATUS.contains(o.pizzaStatus)).collect(Collectors.toList());
    }

    /**
     * EnumMap
     */
    public static EnumMap<PizzaStatus, List<Pizza>> groupPizzaByStatus(List<Pizza> pizzas) {
        EnumMap<PizzaStatus, List<Pizza>> pizzaMap = Maps.newEnumMap(PizzaStatus.class);

        pizzaMap = pizzas.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(Pizza::pizzaStatus, () -> Maps.newEnumMap(PizzaStatus.class), Collectors.toList()));

        // for (Pizza pizza : pizzas) {
        //     if (pizzaMap.containsKey(pizza.pizzaStatus)) {
        //         pizzaMap.get(pizza.pizzaStatus).add(pizza);
        //     } else {
        //         pizzaMap.put(pizza.pizzaStatus, Lists.newArrayList(pizza));
        //     }
        // }

        return pizzaMap;
    }

}
