package com.hframework.mq.receiver;

import com.hframework.common.helper.LogHelper;
import com.hframework.common.util.StringUtils;
import com.hframework.mq.MqConfig;
import com.hframework.mq.MqMessage;
import com.hframework.mq.processor.MqProcessor;
import org.apache.log4j.Logger;

/**
 * User: zhangqh6
 * Date: 2016/3/18 15:34:34
 */
public class DefaultMqReceiver implements MqReceiver{

    private static final Logger logger = Logger.getLogger(DefaultMqReceiver.class);

    private MqConfig mqConfig;
    /**
     * 消息接收
     * @param mqMessage
     * @return
     */
    public void receive(MqMessage mqMessage) {
        logger.debug(LogHelper.begin("MQ消息接收",mqMessage));

        String exchangeName = mqMessage.getExchangeName();
        String queueName = mqMessage.getQueueName();
        Object messageObject = mqMessage.getMessageObject();

        if(StringUtils.isBlank(exchangeName) || StringUtils.isBlank(queueName) || messageObject == null) {
            logger.info(LogHelper.check("MQ消息接收",exchangeName,queueName,messageObject));
            return ;
        }

        MqProcessor process = mqConfig.getProcess(exchangeName, queueName);
        process.process(messageObject);

        logger.debug(LogHelper.end("MQ消息接收", mqMessage));
    }
}
