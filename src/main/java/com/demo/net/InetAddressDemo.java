package com.demo.net;

import java.net.InetAddress;
import java.util.Arrays;

/**
 * InetAddress
 *
 * @author developer.wang
 * @date 2020/3/22
 * @see InetAddress
 */
public class InetAddressDemo {

    public static void main(String[] args) {
        try {
            // InetAddress address = InetAddress.getByName("www.alipay.com");
            // System.out.println(address);
            // System.out.println(String.format("address: %s", Arrays.toString(address.getAddress())));
            // System.out.println(String.format("canonicalName: %s", address.getCanonicalHostName()));
            // System.out.println(String.format("hostName: %s", address.getHostName()));
            // System.out.println(String.format("hostAddress: %s", address.getHostAddress()));
            // System.out.println(String.format("isReachable: %s", address.isReachable(1000)));


            // 根据原始Ip地址来获取对应的InetAddress实例
            InetAddress localAddress = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
            System.out.println(String.format("localAddress: %s", Arrays.toString(localAddress.getAddress())));
            System.out.println(String.format("localAddress hostName: %s", localAddress.getHostName()));
            System.out.println(String.format("localAddress hostAddress: %s", localAddress.getHostAddress()));

            // 获取该InetAddress实例对应的全限定域名
            System.out.println(String.format("localAddress canonicalName: %s", localAddress.getCanonicalHostName()));

            // 判断是否可达
            System.out.println(String.format("localAddress isReachable: %s", localAddress.isReachable(200)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
