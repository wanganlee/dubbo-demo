package com.demo.balance;

/**
 * 指定dubbo服务IP调用
 *
 * @author developer.wang
 * @date 2020/2/27
 */
public class RpcInvoker {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void set(String ip) {
        holder.set(ip);
    }

    //***************** 访问级别不暴露给非同包的其他类  *****************************//

    static void remove() {
        holder.remove();
    }

    static String get() {
        return holder.get();
    }
}
