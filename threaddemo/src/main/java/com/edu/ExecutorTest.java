package com.edu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */
public class ExecutorTest implements  Runnable{
    private CountDownLatch countDownLatch = new CountDownLatch(10);
    public void run() {
        System.out.println(System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) {
        ExecutorTest executorTest = new ExecutorTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {
            executorService.submit(executorTest);
        }
        try {
            executorTest.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
