package com.hframework.mq;


import com.hframework.mq.utils.HessianSerializeFactory;

import java.io.IOException;

/**
 * User: zhangqh6
 * Date: 2016/3/18 13:58:58
 * MQ传递与接收消息的载体
 */
public class MqMessage<T> {

    private String exchangeName;

    private String queueName;

    private T messageObject ;

    private byte[] messageBytes;

    public MqMessage(){};

    public MqMessage(String exchangeName, T t) {
        this.exchangeName = exchangeName;
        this.messageObject = t;
    }


    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public T getMessageObject() {
        return messageObject;
    }

    public void setMessageObject(T messageObject) {
        this.messageObject = messageObject;
    }

    public byte[] getMessageBytes() {

        if(messageBytes == null) {
            try {
                this.messageBytes = HessianSerializeFactory.getInstance().serialize(messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return messageBytes;
    }

    public void setMessageBytes(byte[] messageBytes) {
        this.messageBytes = messageBytes;
    }
}
