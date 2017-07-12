package com.hframework.mq.processor;

/**
 * User: zhangqh6
 * Date: 2016/3/18 15:35:35
 */
public abstract class AbstractMqProcessor implements MqProcessor {

    public void process(Object object) {
        processInternal(object);
    }

    public abstract void processInternal(Object object) ;
}
