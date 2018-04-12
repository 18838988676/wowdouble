package com.chart1;

/**
 * 线程无序
 *
 private void init(ThreadGroup g, Runnable target, String name,
 long stackSize, AccessControlContext acc,
 boolean inheritThreadLocals)
 */
public class CreateThread {
    private static class Work extends  Thread{
        public void run(){
            System.out.println("working");
        }
    }

    private static class Work2 implements Runnable{

        public void run() {
            System.out.println("working");
        }
    }

    public static void main(String[] args) {
        Thread work = new Thread(new Work());
        work.start();
        Thread work2 = new Thread(new Work2());
        work2.start();
    }

}
