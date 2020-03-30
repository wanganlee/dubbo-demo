package com.demo.lang;

/**
 * 参数传递方式
 *
 * @author developer.wang
 * @date 2020/3/17
 */
public class ParamTransfer {

    public static void main(String[] args) {
        // int num1 = 10;
        // int num2 = 20;
        // swap(num1, num2);
        // System.out.println(String.format("num1 = %s", num1));
        // System.out.println(String.format("num2 = %s", num2));


        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(array[0]);
        change(array);
        System.out.println(array[0]);
    }


    /**
     * 方法不能修改基本类型的参数值
     */
    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println(String.format("a = %s", a));
        System.out.println(String.format("b = %s", b));
    }


    private static void change(int[] array) {
        // 将数组的第一个元素变为0
        array[0] = 0;
    }
}
