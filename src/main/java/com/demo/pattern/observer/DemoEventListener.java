package com.demo.pattern.observer;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 定义一个事件监听者，实现ApplicationListener接口，重写onApplicationEvent方法
 *
 * @author developer.wang
 * @date 2020/3/21
 */
@Component
public class DemoEventListener implements ApplicationListener<DemoEvent> {


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(DemoEvent event) {
        String message = event.getMessage();
        System.out.println(String.format("接收到的消息是：%s", message));
    }
}
