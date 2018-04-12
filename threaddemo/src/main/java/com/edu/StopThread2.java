package com.edu;

/**
 * 中断线程,结束线程,
 * 1.interrupt().是实例方法,调用之后给线程打上停止标记了,具体何时停止需要逻辑判断,
 * 通过使用 Thread.isInterrupted(); 来进行判断.
 * 2.interrupt(). 的时候如果线程刚好进入阻塞状态, 那么会抛出异常, 这时候标记会被清除,线程无法终止,
 * 需要捕获异常在异常块中再次调用interrupt() 方法,
 * 3.interrupt(),方法与标记类似,但是可以把阻塞状态的线程停止掉.
 */
public class StopThread2 {
    private static class InterThread implements Runnable {

        public void run() {
            while (true) {
                Thread.yield();
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new InterThread());
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
}
