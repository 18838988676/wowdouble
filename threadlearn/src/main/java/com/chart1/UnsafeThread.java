package com.chart1;

public class UnsafeThread {


    private static class Work extends  Thread{
        public    int count = 0;
        @Override
        public void run() {
            super.run();
            count++;
            System.out.println(count);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Work work = new Work();
        Thread a = new Thread(work,"1");
        Thread b = new Thread(work,"2");
        Thread c = new Thread(work,"3");
        Thread f = new Thread(work,"4");
        Thread d = new Thread(work,"5");
        Thread e = new Thread(work,"6");
        a.start();
        b.start();
        c.start();
        f.start();
        d.start();
        e.start();
        Thread.sleep(1000);
        System.out.println(work.count);
    }

}
