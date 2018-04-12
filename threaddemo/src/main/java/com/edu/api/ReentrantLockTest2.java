package com.edu.api;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可以中断
 */
public class ReentrantLockTest2 {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private static  class T1 implements Runnable{
        public void run() {
            try {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    private static  class T2 implements Runnable{
        public void run() {
            try {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());
        t1.start();
        t2.start();
        Thread.sleep(3000);
        t2.interrupt();
        t1.join();
        t2.join();
        System.out.println("end");
    }
}
