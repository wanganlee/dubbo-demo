package com.demo.balance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Dubbo负载均衡指定服务IP调用实现（实现规则）
 * <p>
 * 如果只有一个dubbo服务提供者，那么直接返回
 * <p>
 * 如果RpcInvoker中没有指定ip，使用dubbo默认的随机权重负载均衡
 * <p>
 * 如果RpcInvoker中指定了ip，但是提供者列表中没有这个ip，依然使用dubbo默认的随机权重负载均衡 *
 * </p>
 *
 * @author developer.wang
 * @date 2020/2/27
 * @see com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance
 */
public class DispatcherLoadBalance extends RandomLoadBalance {

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        String ip = RpcInvoker.get();
        if (Objects.isNull(ip)) {
            return super.doSelect(invokers, url, invocation);
        }
        RpcInvoker.remove();
        Invoker<T> invoker = invokers.stream().filter(Objects::nonNull).filter(o -> com.alibaba.dubbo.common.utils.StringUtils.isEquals(ip, o.getUrl().getHost()))
                .findFirst().orElse(null);
        if (Objects.nonNull(invoker)) {
            return invoker;
        }
        return super.doSelect(invokers, url, invocation);
    }
}
