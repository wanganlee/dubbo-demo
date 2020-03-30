package com.demo.event;

import java.util.EventListener;

/**
 * 事件监听接口
 *
 * @author developer.wang
 * @date 2020/3/2
 */
public interface MethodMonitorEventListener extends EventListener {

    /**
     * 处理方法执行之前发布的事件
     */
    void onMethodBegin(MethodMonitorEvent event);

    /**
     * 处理方法执行之后发布的事件
     */
    void onMethodEnd(MethodMonitorEvent event);
}
