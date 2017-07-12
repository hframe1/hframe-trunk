package com.hframework.mq;


import com.hframework.common.helper.LogHelper;
import com.hframework.mq.exception.MqMessageErrorHandler;
import com.hframework.mq.receiver.DefaultMqReceiver;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * User: zhangqh6
 * Date: 2016/3/18 16:37:37
 */
public class MqBootstrap {

    private static final Logger logger = Logger.getLogger(MqBootstrap.class);

    private MqConfig mqConfig;

    private CachingConnectionFactory connectionFactory;

    private RabbitAdmin rabbitAdmin;

    private SimpleMessageListenerContainer msgListenerContainer; // rabbitMQ msg listener container

    private MessageConverter serializerMessageConverter = new SerializerMessageConverter(); // 直接指定


    //queue cache, key is exchangeName
    private Map<String, DirectExchange> exchanges = new HashMap<String,DirectExchange>();
    //queue cache, key is queueName
    private Map<String, Queue> queues = new HashMap<String, Queue>();

    /**
     * 是否启动
     */
    private AtomicBoolean isStarted = new AtomicBoolean(false);


    public void init() throws Exception {
        logger.debug(LogHelper.begin("MQ守护进程初始化"));

        if(connectionFactory == null) {
            initConnectionFactory();
        }

        Map<String, Class> bindInfo = mqConfig.getBindInfo();
        for (String keys : bindInfo.keySet()) {
            String[] split = keys.split("\\|");
            if(split.length != 2) {
                Exception ex = new Exception("配置错误");
                logger.error(LogHelper.error("MQ守护进程初始化", ex));
                throw ex;
            }
            declareExchangeAndQueue(split[0], split[1]);
        }

        startMessageListenerContainer();

        logger.debug(LogHelper.end("MQ守护进程初始化"));
    }

    private void startMessageListenerContainer() {
        logger.debug(LogHelper.begin("启动消息监听"));

        msgListenerContainer = new SimpleMessageListenerContainer();
        msgListenerContainer.setConnectionFactory(connectionFactory);
        msgListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        msgListenerContainer.setMessageListener(new MessageListenerAdapter(new DefaultMqReceiver(), serializerMessageConverter));
        msgListenerContainer.setErrorHandler(new MqMessageErrorHandler());
        msgListenerContainer.setPrefetchCount(mqConfig.getPrefetchSize()); // 设置每个消费者消息的预取值
        msgListenerContainer.setConcurrentConsumers(mqConfig.getEventMsgProcessNum());
        msgListenerContainer.setTxSize(mqConfig.getPrefetchSize());//设置有事务时处理的消息数
        msgListenerContainer.setQueues(queues.values().toArray(new Queue[queues.size()]));
        msgListenerContainer.start();
        logger.debug(LogHelper.end("启动消息监听"));

    }

    private void declareExchangeAndQueue(String exchangeName, String queueName) {
        logger.debug(LogHelper.begin("RabbitMQ注册", exchangeName, queueName));
        String bindKey = exchangeName + "." + queueName;

        if(mqConfig.getProcess(exchangeName,queueName) != null) {
            return ;
        }

        Queue queue = queues.get(queueName);
        if(queue == null) {
            queue = new Queue(queueName,true,false, false);
            queues.put(queueName, queue);
            rabbitAdmin.declareQueue(queue);
            logger.debug(LogHelper.end("RabbitMQ注册队列", queue));
        }

        DirectExchange directExchange = exchanges.get(exchangeName);
        if(directExchange == null) {
            directExchange = new DirectExchange(exchangeName,true,false);
            exchanges.put(exchangeName, directExchange);
            rabbitAdmin.declareExchange(directExchange);
            logger.debug(LogHelper.end("RabbitMQ注册交换机", directExchange));
        }

        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(bindKey);
        rabbitAdmin.declareBinding(binding);
        logger.debug(LogHelper.end("RabbitMQ注册绑定关系", binding));

        logger.debug(LogHelper.end("RabbitMQ注册", exchangeName, queueName));
    }

    private void initConnectionFactory() {
        logger.debug(LogHelper.begin("连接工厂缓存"));

        connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(mqConfig.getServerHost());
        connectionFactory.setChannelCacheSize(mqConfig.getEventMsgProcessNum());
        connectionFactory.setPort(mqConfig.getPort());
        connectionFactory.setUsername(mqConfig.getUsername());
        connectionFactory.setPassword(mqConfig.getPassword());
        if (!StringUtils.isEmpty(mqConfig.getVirtualHost())) {
            connectionFactory.setVirtualHost(mqConfig.getVirtualHost());
        }
        logger.debug(LogHelper.end("连接工厂缓存"));

    }


    public void start() {
        logger.debug(LogHelper.begin("MQ守护进程启动"));
        if(isStarted.get()) {
            logger.info(LogHelper.check("MQ守护进程启动", "isStarted", isStarted));
        }



        logger.debug(LogHelper.end("MQ守护进程启动"));
    }

}
