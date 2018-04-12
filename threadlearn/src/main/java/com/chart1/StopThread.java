package com.chart1;

/**
 * 停止线程  stop interrupt() interrupted();
 *
 * 1.使用退出标志 ,任务完成后,修改标志, 根据标志决定退出
 * 2.stop强行终止
 * 3.interrupt() 终止.
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        Work4 work4 = new Work4();
        work4.start();
        Thread.sleep(1000);
        work4.setStop(true);

        Work5 work5 = new Work5();
        work5.start();
        work5.interrupt();
        System.out.println("------------"+work5.isInterrupted());

        Work6 work6 = new Work6();
        work6.start();
        Thread.sleep(2000);
        work6.interrupt();

        Work7 work7 = new Work7();
        work7.start();
        Thread.sleep(2000);
        work7.interrupt();
        work7.join();
        System.out.println(".....");
    }
}

class  Work4  extends Thread{
    private volatile boolean stop ;
    @Override
    public void run() {
        super.run();
        while (true){
            System.out.println("working");
            if(stop) break;
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}

class  Work5 extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("work5 is working ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //打上中断标记
            this.interrupt();
            // //测试当前线程是否中断,不清除中断标记
            System.out.println("isInterrupted?"+this.isInterrupted());
            System.out.println("isInterrupted?"+this.isInterrupted()); //仍然有标记
            //测试当前线程是否中断,并清除中断标记
            System.out.println("interrupted?"+this.interrupted());
            System.out.println("interrupted?"+this.interrupted()); //没有了标记
            System.out.println("isInterrupted?"+this.isInterrupted());//没有了标记
        }

    }
}

class Work6 extends Thread{
    @Override
    public void run() {
        super.run();
        while (true){
            if (this.isInterrupted())
                break;
            System.out.println("work6 is working ");
        }
        System.out.println("still working ");
    }
}

class Work7 extends Thread{
    @Override
    public void run() {
        super.run();
        try {
        while (true){
            //通过异常的方式结束 这样 方法之后的都不执行了,并且可以把线程停止的消息往上传递
            if (this.isInterrupted()) throw  new InterruptedException();
            System.out.println("work7 is working ");
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("still working ");
    }
}

class Work8 extends Thread{
    @Override
    public void run() {
        super.run();
            while (true){
                //通过return 的方式结束
                if (this.isInterrupted()) return;
                System.out.println("work7 is working ");
            }
    }
}