package com.edu.api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁, 可以是公平锁,也可以是非公平锁.
 * 可以中断锁,等待锁,尝试锁,释放锁.
 * 可以配合condition实现挂起唤醒
 */
public class ReentrantLockTest  {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WorkThread());
        Thread thread2 = new Thread(new WorkThread());
        thread.start();thread2.start();
        thread.join();thread2.join();
        System.out.println(WorkThread.count);
    }

    private static class WorkThread implements Runnable{
        private static Lock lock = new ReentrantLock();
        private static int count = 0;
        public void run() {
            for (int i = 0; i <1000000 ; i++) {
                try{
                    lock.lock();
                    count++;
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
