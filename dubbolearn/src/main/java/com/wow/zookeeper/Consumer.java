package com.wow.zookeeper;

import com.wow.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-dubbo-zookeeper-consumer.xml");
        context.start();
        // 获取服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        // 服务执行
        String hello = demoService.sayHello("world");

        System.out.println(hello);
    }
}
