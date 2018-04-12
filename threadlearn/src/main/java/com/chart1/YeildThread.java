package com.chart1;

/**
 * 让出CPU
 */
public class YeildThread {
    public static void main(String[] args) {
        Work10 work10 = new Work10();
        work10.start();
    }
}

class Work10 extends Thread{
    private int count=0;
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        super.run();
        for (int i = 0; i <500000 ; i++) {
          //  Thread.yield();
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }
}