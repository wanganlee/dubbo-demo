package com.demo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @author developer.wang
 * @date 2020/2/26
 */
public class DubboSPITest {

    @Test
    public void test() {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimus = extensionLoader.getExtension("optimus");
        optimus.sayHello();

        Robot bee = extensionLoader.getExtension("bee");
        bee.sayHello();
    }
}
