package com.hframework.mq.processor;

/**
 * User: zhangqh6
 * Date: 2016/3/18 15:38:38
 */
public class SimpleMqProcessor extends AbstractMqProcessor implements MqProcessor {

    public void processInternal(Object object) {
        System.out.println(object);
    }

}
