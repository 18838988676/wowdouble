package com.edu.api;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可以等待时限
 */
public class ReentrantLockTest3 implements Runnable{
    private static ReentrantLock lock = new ReentrantLock();

    public void run() {
        try {
            if(            lock.tryLock(5, TimeUnit.SECONDS)
){
                Thread.sleep(6000);
            }else {
                System.out.println("get lock  faild");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest3 reentrantLockTest3 = new ReentrantLockTest3();
        Thread thread = new Thread(reentrantLockTest3);
        Thread thread1 = new Thread(new ReentrantLockTest3());
        thread.start();
        thread1.start();
    }
}
