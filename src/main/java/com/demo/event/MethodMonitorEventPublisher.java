package com.demo.event;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author developer.wang
 * @date 2020/3/2
 */
public class MethodMonitorEventPublisher {

    private List<MethodMonitorEventListener> listeners = Lists.newArrayList();

    public void methodMonitor() throws InterruptedException {
        MethodMonitorEvent event = new MethodMonitorEvent(this);
        this.publishEvent("begin", event);
        // 模拟方法执行：休眠5秒
        TimeUnit.SECONDS.sleep(5);
        this.publishEvent("end", event);
    }

    private void publishEvent(String status, MethodMonitorEvent event) {
        // 避免在事件处理期间，监听器被移除，这里为了安全做了一个复制操作
        List<MethodMonitorEventListener> copyListeners = Lists.newArrayList(this.listeners);
        if (CollectionUtils.isEmpty(copyListeners)) {
            return;
        }
        copyListeners.stream().filter(Objects::nonNull).forEach(e -> {
            if ("begin".equals(status)) {
                e.onMethodBegin(event);
            } else {
                e.onMethodEnd(event);
            }
        });
    }

    public void addEventListener(MethodMonitorEventListener listener) {
        Objects.requireNonNull(listener, "Listener must not be null");
        this.listeners.add(listener);
    }

    public void removeEventListener(MethodMonitorEventListener listener) {

    }

    public void removeAllListener() {

    }

    public static void main(String[] args) throws InterruptedException {
        MethodMonitorEventPublisher publisher = new MethodMonitorEventPublisher();
        publisher.addEventListener(new AbstractMethodMonitorEventListener());
        publisher.methodMonitor();
    }

}
