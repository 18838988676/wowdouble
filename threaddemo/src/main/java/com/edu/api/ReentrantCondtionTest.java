package com.edu.api;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantCondtionTest implements Runnable{
    private  ReentrantLock reentrantLock = new ReentrantLock();
    private  Condition condition = reentrantLock.newCondition();

    public void run() {
        reentrantLock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantCondtionTest condtionTest = new ReentrantCondtionTest();
        Thread t = new Thread(condtionTest);
        t.start();
        Thread.sleep(2000);
        condtionTest.reentrantLock.lock();
        condtionTest.condition.signal();
        condtionTest.reentrantLock.unlock();
        t.join();
        System.out.println("end");
    }
}
