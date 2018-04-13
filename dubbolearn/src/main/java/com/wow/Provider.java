package com.wow;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 调用过程：
 1.	服务提供方启动时，向 Key:/dubbo/XXXServer/providers 下，添加当前提供者的地址
 2.	并向 Channel:/dubbo/com.foo.BarService/providers 发送 register 事件



 */
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-provider.xml");
        context.start();
        System.out.println("启动成功");
        System.in.read();
    }
}