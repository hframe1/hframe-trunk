package com.hframework.mq.sender;

import com.hframework.common.helper.LogHelper;
import com.hframework.common.util.StringUtils;
import com.hframework.mq.MqMessage;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;

/**
 * User: zhangqh6
 * Date: 2016/3/18 14:52:52
 * 默认消息队列生产者实现
 */
public class DefaultMqSender implements MqSender{

    private static final Logger logger = Logger.getLogger(DefaultMqSender.class);

    private AmqpTemplate amqpTemplate;



    /**
     * 消息发送
     * @param exchangeName
     * @param t
     * @param <T>
     */
    public <T> void send(String exchangeName, T t) {
        this.send(exchangeName,t);
    }

    /**
     * 消息发送
     * @param exchangeName
     * @param t
     * @param <T>
     */
    public <T> void send(String exchangeName, String queueName,  T t) {
        logger.debug(LogHelper.begin("MQ消息发送",exchangeName, t));

        if(StringUtils.isBlank(exchangeName) || t == null) {
            logger.info(LogHelper.check("MQ消息发送", exchangeName, t));
        }

        MqMessage message = new MqMessage(exchangeName, t);

        amqpTemplate.convertAndSend(exchangeName, queueName , message.getMessageBytes());

        logger.debug(LogHelper.end("MQ消息发送", exchangeName, t));
    }
}
