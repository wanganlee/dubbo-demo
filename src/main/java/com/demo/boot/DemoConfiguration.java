package com.demo.boot;

import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author developer.wang
 * @date 2020/3/2
 */
@Configuration
public class DemoConfiguration {

    /**
     * 只有当JdbcTemplateCondition类的条件成立时，才会创建dubboProtocol这个bean。
     * @return
     */
    @Conditional(JdbcTemplateCondition.class)
    @Bean
    public Protocol dubboProtocol() {
        // org.apache.ibatis.session.Configuration
        Thread.class.getClassLoader();
        return new DubboProtocol();
    }

}
