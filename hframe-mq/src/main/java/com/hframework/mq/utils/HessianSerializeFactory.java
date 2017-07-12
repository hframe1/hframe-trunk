package com.hframework.mq.utils;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.hframework.common.helper.LogHelper;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * User: zhangqh6
 * Date: 2016/3/18 14:05:05
 * 序列化工厂（Hessian实现）
 */
public class HessianSerializeFactory implements SerializeFactory{

    private static final Logger logger = Logger.getLogger(HessianSerializeFactory.class);

    private static final SerializeFactory instance = new HessianSerializeFactory();

    /**
     * 序列化
     *
     * @param t 目标对象
     * @return 字节流
     */
    public <T> byte[] serialize(T t) throws IOException {

        logger.debug(LogHelper.begin("序列化对象", t));

        ByteArrayOutputStream baos = null;
        HessianOutput out = null;
        try {
            baos = new ByteArrayOutputStream(1024);
            out = new HessianOutput(baos);
            out.startCall();
            out.writeObject(t);
            out.completeCall();
        } catch (IOException e) {
            logger.debug(LogHelper.error("序列化对象", e));
            throw e;
        }finally {
            if(out != null) {
                baos.close();
            }
        }

        logger.debug(LogHelper.end("序列化对象", t));
        return baos != null ? baos.toByteArray() : null;
    }

    /**
     * 反序列化
     *
     * @param data 字节流
     * @return 目标对象
     */
    public <T> T deserialize(byte[] data) throws Throwable {
        logger.debug(LogHelper.begin("反序列化对象",data));
        HessianInput input = null;
        ByteArrayInputStream bais = null;
        bais = new ByteArrayInputStream(data);
        input = new HessianInput(bais);

        try {
            input.startReply();
            Object t = input.readObject();
            input.completeReply();
            logger.debug(LogHelper.end("反序列化对象", t));
            return (T) t;
        } catch (Throwable t) {
            t.printStackTrace();
            logger.debug(LogHelper.error("反序列化对象", t));
            throw t;
        }finally {
            if(input != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static SerializeFactory getInstance() {
        return instance;
    }

    public static void main(String[] args) throws Throwable {
        byte[] bytes = HessianSerializeFactory.getInstance().serialize("fdsafds");
        System.out.println(bytes);
        System.out.println(HessianSerializeFactory.getInstance().deserialize(bytes));

    }
}
