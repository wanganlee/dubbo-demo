package com.demo.event;

/**
 * @author developer.wang
 * @date 2020/3/2
 */
public class AbstractMethodMonitorEventListener implements MethodMonitorEventListener {
    /**
     * 处理方法执行之前发布的事件
     *
     * @param event
     */
    @Override
    public void onMethodBegin(MethodMonitorEvent event) {
        event.timestamp = System.currentTimeMillis();
    }

    /**
     * 处理方法执行之后发布的事件
     *
     * @param event
     */
    @Override
    public void onMethodEnd(MethodMonitorEvent event) {
        long duration = System.currentTimeMillis() - event.timestamp;
        System.out.println(String.format("耗时：%s", duration));
    }
}
