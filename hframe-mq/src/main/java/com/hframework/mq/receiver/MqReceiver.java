package com.hframework.mq.receiver;


import com.hframework.mq.MqMessage;

/**
 * User: zhangqh6
 * Date: 2016/3/18 15:27:27
 */
public interface MqReceiver<T> {

    /**
     * 消息接收
     * @param mqMessage
     * @return
     */
    public void receive(MqMessage<T> mqMessage) ;

}
