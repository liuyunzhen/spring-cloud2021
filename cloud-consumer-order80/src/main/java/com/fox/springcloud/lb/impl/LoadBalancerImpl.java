package com.fox.springcloud.lb.impl;

import com.fox.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getAndIncrement(int count){
        for (;;){
            int current = atomicInteger.get();
            int next = (current + 1) % count;
            if (atomicInteger.compareAndSet(current, next)){
                return next;
            }
        }
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> instances) {
        int index = getAndIncrement(instances.size());
        return instances.get(index);
    }
}
