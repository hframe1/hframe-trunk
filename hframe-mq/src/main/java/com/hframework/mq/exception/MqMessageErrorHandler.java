package com.hframework.mq.exception;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;

/**
 * User: zhangqh6
 * Date: 2016/3/18 17:34:34
 */
public class MqMessageErrorHandler implements ErrorHandler {
    private static final Logger logger = Logger.getLogger(MqMessageErrorHandler.class);

    public void handleError(Throwable t) {
        logger.error("RabbitMQ happen a error:" + t.getMessage(), t);
    }

}
