package com.edu;

public class MutilThread {
    private static  Long num ;
    private static class  ChangeNum implements Runnable{
        public void run() {
            while (true){
                MutilThread.num = to ;
                Thread.yield();// 操作一次就让出时间片 ,防止重复
            }
        }
        private  Long to ;

        public ChangeNum(Long to) {
            this.to = to;
        }
    }

    private static  class ReadNum implements Runnable{
        public void run() {
            while (true){
                long tmp = num;
                // 这里 需要先赋值给一个变量, 因为 num 一直在变化,不然的话判断和输出直接会有其他线程介入,引起值不一致.
                if (tmp!=111L&&tmp!=333L&&tmp!=-999L&&tmp!=-444L){
                    System.out.println(tmp);
                    Thread.yield();//防止重复
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeNum(111L)).start();
        new Thread(new ChangeNum(333L)).start();
        new Thread(new ChangeNum(-444L)).start();
        new Thread(new ChangeNum(-999L)).start();
        new Thread(new ReadNum()).start();
    }
}
