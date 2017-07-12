package com.hframework.mq.processor;

/**
 * User: zhangqh6
 * Date: 2016/3/18 15:35:35
 */
public interface MqProcessor<T> {

    public void process(T object);
}
