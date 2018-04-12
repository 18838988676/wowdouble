package com.edu;

/**
 * 使用wait和notify
 */
public class WaitAndNotifyTest {
    private static class T1 extends Thread{
        @Override
        public void run() {
            System.out.println("T1 working");
            synchronized (this){
                System.out.println("T1 enter lock ");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1 out lock");
            }
        }
    }

    private static class T2 extends Thread{
        @Override
        public void run() {
            System.out.println("T2 start");
            synchronized (this){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2 notify T1 ");
                notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("end");
    }
}
