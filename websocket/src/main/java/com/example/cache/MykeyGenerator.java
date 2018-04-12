package com.example.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MykeyGenerator implements KeyGenerator {
    /**
     *
     * @param o  对象
     * @param method 方法名
     * @param objects  参数列表数组
     * @return
     */
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        //定义自己的key生成规则
        String key = (String) objects[0];
        return key;
    }
}
