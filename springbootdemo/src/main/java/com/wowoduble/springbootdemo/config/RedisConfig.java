package com.wowoduble.springbootdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "redis")
public class RedisConfig {

    private String host ;

    private int  port ;

    private  boolean ssl ;

    private String auth ;

    private int maxActive ;

    private int maxIdle ;

    private  int maxWait ;

    private int timeout ;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private  boolean TEST_ON_BORROW = true;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int  getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }


    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isTEST_ON_BORROW() {
        return TEST_ON_BORROW;
    }

    public void setTEST_ON_BORROW(boolean TEST_ON_BORROW) {
        this.TEST_ON_BORROW = TEST_ON_BORROW;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", ssl=" + ssl +
                ", auth='" + auth + '\'' +
                ", maxActive=" + maxActive +
                ", maxIdle=" + maxIdle +
                ", maxWait=" + maxWait +
                ", timeout=" + timeout +
                ", TEST_ON_BORROW=" + TEST_ON_BORROW +
                '}';
    }
}
