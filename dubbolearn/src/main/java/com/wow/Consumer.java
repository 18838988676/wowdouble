package com.wow;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 3.	服务消费方启动时，从 Channel:/dubbo/com.foo.BarService/providers 订阅 register 和 unregister 事件
 4.	并向 Key:/dubbo/com.foo.BarService/providers 下，添加当前消费者的地址
 5.	服务消费方收到 register 和 unregister 事件后，从 Key:/dubbo/com.foo.BarService/providers 下获取提供者地址列表
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-dubbo-consumer.xml");
        context.start();
        // 获取服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        // 服务执行
        String hello = demoService.sayHello("world");

        System.out.println(hello);
    }
}
