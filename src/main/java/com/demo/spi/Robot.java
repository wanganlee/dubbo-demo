package com.demo.spi;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author developer.wang
 * @date 2020/2/26
 */
@SPI
public interface Robot {

    void sayHello();
}
