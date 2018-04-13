package com.wow.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.wow.DemoService;
import com.wow.DemoServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 其实API的方式也是生成配置对象,可以参考
 * 配置的图中每个配置都有对应的类.
 *
 */
public class ApiProvider {
    ApplicationConfig applicationConfig;
    private final RegistryConfig registryConfig;
    protected final ProtocolConfig protocolConfig;
    DemoServiceImpl demoService;

    private List<ServiceConfig> services = new ArrayList();


    public ApiProvider() {
        //配置APP
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName("api-demo-provider");

        //注册中心
        registryConfig = new RegistryConfig();
        registryConfig.setProtocol("multicast");
        //组播受网络结构限制，只适合小规模应用或开发阶段使用。组播地址段: 224.0.0.0 - 239.255.255.255
        registryConfig.setAddress("224.5.6.7:2222");
        //协议
        protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        //声明bean
        demoService = new DemoServiceImpl();

    }

    public void exportService(Class service) {
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setRef(demoService);
        serviceConfig.setInterface("com.wow.DemoService");
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        //上传注册中心
        serviceConfig.export();
        System.out.println(String.format("%s服务启动成功 端口20880", service.getSimpleName()));
        services.add(serviceConfig);
    }

    public static void main(String[] args) throws IOException {
        ApiProvider provider = new ApiProvider();
        provider.exportService(DemoService.class);
        System.in.read();
    }

}
