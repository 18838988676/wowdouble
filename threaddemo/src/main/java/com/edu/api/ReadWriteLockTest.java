package com.edu.api;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLockTest {
    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock lock2 = new ReentrantReadWriteLock();
    private Lock readL = lock2.readLock();
    private Lock writeL = lock2.writeLock();

    private int value;

    public Object handlerRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            // read lock
            Thread.sleep(1000);
            System.out.println("read");
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handlerWrite(Lock lock, int value) throws InterruptedException {
        lock.lock();
        //write
        this.value = value;
        Thread.sleep(1000);
        System.out.println("write");
        lock.unlock();
    }

    public static void main(String[] args) {
        final ReadWriteLockTest demo = new ReadWriteLockTest();
        Runnable readRunnable = new Runnable() {
            public void run() {
                try {
                      demo.handlerRead(demo.lock);
                    //  demo.handlerRead(demo.readL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            public void run() {
                try {
                  demo.handlerWrite(demo.lock, new Random().nextInt());
                    //  demo.handlerWrite(demo.writeL, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
