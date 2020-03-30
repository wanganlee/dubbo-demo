package com.demo.pattern.observer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 使用事件发布者发布消息，可以通过ApplicationEventPublisher的publishEvent()方法发布消息
 *
 * @author developer.wang
 * @date 2020/3/21
 */
@Component
public class DemoEventPublisher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void publish(String message) {
        // 发布事件
        this.applicationContext.publishEvent(new DemoEvent(this, message));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
