package com.edu.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class MyTask implements Runnable {
    private String name ;

    public MyTask(String name) {
        this.name = name;
    }

    public void run() {

    System.out.println(name + " is running ");
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                5,10,30L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10)
        ){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(Thread.currentThread().getName()+"ready run");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(Thread.currentThread().getName()+"after run");
            }

            @Override
            protected void terminated() {
                System.out.println(Thread.currentThread().getName()+"over");
            }
        };

        for (int i = 0; i <16 ; i++) {
            executorService.submit(new MyTask(i+""));
        }
        executorService.shutdown();
    }
}
