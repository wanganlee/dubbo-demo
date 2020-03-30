package com.demo.spi;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author developer.wang
 * @date 2020/2/26
 */
@Service
public class OptimusPrime implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am OptimusPrime.");
    }
}
