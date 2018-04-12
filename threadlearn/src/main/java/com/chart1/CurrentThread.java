package com.chart1;

public class CurrentThread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Work work = new Work();
        work.start();
        System.out.println("-------------------");
        Thread thread = new Thread(work);
        thread.setName("t");
        thread.start();
    }
}

class Work extends Thread{
    public Work() {
        System.out.println("Thread="+Thread.currentThread().getName());
        System.out.println("this="+this.getName());
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Thread="+Thread.currentThread().getName());
        System.out.println("this="+this.getName());
    }
}