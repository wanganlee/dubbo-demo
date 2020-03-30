package com.demo.event;

import java.util.EventObject;

/**
 * 简单的监控一个方法的执行耗时
 *
 * @author developer.wang
 * @date 2020/3/2
 */
public class MethodMonitorEvent extends EventObject {

    /**
     * 时间戳，用于记录方法开始执行的时间
     */
    public long timestamp;

    public MethodMonitorEvent(Object source) {
        super(source);
    }
}
