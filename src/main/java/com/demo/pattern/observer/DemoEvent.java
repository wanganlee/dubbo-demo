package com.demo.pattern.observer;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 设计模式：观察者模式
 * Spring事件驱动模型就是观察者模式很经典的一个应用
 * </p>
 * <p>
 * 定义一个事件，继承自ApplicationEvent并且重写相应的构造函数
 *
 * @author developer.wang
 * @date 2020/3/21
 */
public class DemoEvent extends ApplicationEvent {

    private String message;

    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
