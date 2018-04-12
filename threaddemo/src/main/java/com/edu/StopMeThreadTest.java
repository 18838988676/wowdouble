package com.edu;

/**
 * 这里必须使用 volatile 保证可见性,
 * 如果不用valatile 第一次访问stop的时候通过this引用把值读到栈上 之后每次就直接从栈上读值了 你改了stop他还是这样读 所以循环卡在那里不会往下走
 */
public class StopMeThreadTest  {
    private static class StopMeThread extends  Thread{
        private volatile boolean stop ;
        private volatile String a = "1";
        @Override
        public void run() {
            while (true){
                if (a.equals("2")){
                    break;
                }
            }
            System.out.println("stop myself by stopMe ! ");
        }

        public void stopMe(){
            stop = true;
            a = "2";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StopMeThread t = new StopMeThread();
        t.start();
        Thread.sleep(100);
        t.stopMe();
    }
}
