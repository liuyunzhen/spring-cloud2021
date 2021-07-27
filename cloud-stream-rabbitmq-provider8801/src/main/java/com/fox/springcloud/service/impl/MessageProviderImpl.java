package com.fox.springcloud.service.impl;


import com.fox.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)//定义消息的推送管道
public class MessageProviderImpl implements MessageProvider {
    @Resource
    private MessageChannel output;//变量名必须是output,应该是来自配置文件中通道的名字

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("生产者输出消息:" + uuid);
        return null;
    }
}
