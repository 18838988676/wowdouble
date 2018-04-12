package com.edu;

public class ThreadGroupTest {

    private static class TestThread implements Runnable{

        public void run() {
            System.out.println("I am "+Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("testThread");
        Thread thread = new Thread(threadGroup, new TestThread(),"T1");
        Thread thread2 = new Thread(threadGroup, new TestThread(),"T2");
        thread.start();thread2.start();
        System.out.println(threadGroup.activeCount());
        System.out.println(threadGroup.activeGroupCount());
        threadGroup.setMaxPriority(4);
        threadGroup.list();
    }
}
