package com.demo.lang;

import java.util.Comparator;

/**
 * 基本类型，包装类型
 *
 * @author developer.wang
 * @date 2020/3/25
 */
public class AutoBoxing {

    public static void main(String[] args) {
        Comparator<Integer> comparator = (o1, o2) -> o1 > o2 ? 1 : (o1 == o2 ? 0 : -1);

        // Comparator<Integer> comparator = new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer o1, Integer o2) {
        //         return o1 < o2 ? -1 : (o1 == o2 ? 0 : 1);
        //     }
        // }

        int result = comparator.compare(new Integer(42), new Integer(42));
        System.out.println(result);
    }
}
