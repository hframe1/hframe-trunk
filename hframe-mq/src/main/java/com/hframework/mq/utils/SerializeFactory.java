package com.hframework.mq.utils;

import java.io.IOException;

/**
 * User: zhangqh6
 * Date: 2016/3/18 14:02:02
 * 序列化工厂
 */
public interface SerializeFactory {

    /**
     * 序列化
     * @param t 目标对象
     * @param <T> 目标类
     * @return 字节流
     */
    public <T> byte[] serialize(T t) throws IOException;

    /**
     * 反序列化
     * @param data 字节流
     * @param <T> 目标类
     * @return 目标对象
     */
    public <T> T deserialize(byte[] data) throws Throwable;
}
