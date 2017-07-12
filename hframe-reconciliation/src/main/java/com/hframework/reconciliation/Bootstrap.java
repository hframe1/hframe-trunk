package com.hframework.reconciliation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * Created by zhangquanhong on 2016/5/18.
 */
public class Bootstrap {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
    }

}
