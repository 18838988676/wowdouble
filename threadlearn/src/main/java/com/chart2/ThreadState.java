package com.chart2;

/**
 * 线程状态
 */
public class ThreadState{
    private static class StateWork implements Runnable{
        public StateWork() {
            System.out.println("create Thread ,Thread State is "+Thread.currentThread().getState());
        }

        public void run() {

            System.out.println("working,Thread State is "+Thread.currentThread().getState());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StateWork stateWork = new StateWork();
        Thread workThread = new Thread(stateWork);
        System.out.println("before start.."+workThread.getState());
        workThread.start();
        Thread.sleep(1000);
        System.out.println("after start .."+workThread.getState());
        Thread.sleep(1000);
        System.out.println(workThread.getState());
        /**
         create Thread ,Thread State is RUNNABLE
         before start..NEW
         working,Thread State is RUNNABLE
         after start ..TIMED_WAITING
         TERMINATED
         */
    }
}
