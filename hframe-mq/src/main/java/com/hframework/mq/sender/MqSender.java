package com.hframework.mq.sender;

/**
 * User: zhangqh6
 * Date: 2016/3/18 14:50:50
 * 默认消息队列生产者实现
 */
public interface MqSender {

    /**
     * 消息发送
     * @param exchangeName
     * @param t
     * @param <T>
     */
    public <T> void send(String exchangeName, T t);

    /**
     * 消息发送
     * @param exchangeName
     * @param t
     * @param <T>
     */
    public <T> void send(String exchangeName, String queueName, T t);
}
