package com.chart1;

public class IsAliveThread {
    public static void main(String[] args) throws InterruptedException {
        Work2 work2 = new Work2();
        System.out.println(work2.isAlive());
        work2.start();
        Thread.sleep(1000);  //注释掉 最后可能为  true
        System.out.println(work2.isAlive());
    }
}

class Work2 extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println(isAlive());
    }
}