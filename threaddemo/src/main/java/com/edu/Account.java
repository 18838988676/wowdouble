package com.edu;

/**
 * 作为锁的对象 最好是不变的.
 */
public class Account implements Runnable {
    private static Object lock1 = new Object();
    private Object lock2 = new Object();
    private static  int count = 0 ;
    private static  int count2 = 0 ;
    private static  int count3 = 0 ;
    private static  int count4 = 0 ;
    public void run() {
        for (int i = 0; i <1000000 ; i++) {
           increase();
           increase2();
            increase3();
            increase4();
        }
    }

    private void increase() {
        synchronized (lock1){
            count++;
        }
    }
    private void increase2() {
        synchronized (lock2){
            count2++;
        }
    }
    private synchronized void increase3(){
        count3++;
    }
    private static synchronized void increase4(){
        count4++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Account());
        Thread thread2 = new Thread(new Account());
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(count);
        System.out.println(count2);
        System.out.println(count3);
        System.out.println(count4);
    }
}
