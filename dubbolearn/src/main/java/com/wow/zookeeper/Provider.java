package com.wow.zookeeper;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  需要 ZKCLIENT ,logfj
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-zookeeper-provider.xml");
        context.start();
        System.out.println("启动成功");
        System.in.read();
    }
}