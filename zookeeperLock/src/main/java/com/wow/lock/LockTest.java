package com.wow.lock;

import com.wow.ZookeeperUtil;

public class LockTest implements  Runnable {
    static  int i=0;

    DistributeLock lock=new DistributeLock(ZookeeperUtil.connectString,"/lock");
    @Override
    public void run() {
        lock.lock();
        for(int j=0;j<1000000;j++){
                i++;

        }
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest=new LockTest();
        Thread thread=new Thread(lockTest);
        Thread thread2=new Thread(lockTest);
        thread.start();thread2.start();
        thread.join();thread2.join();
        System.out.println(i);

    }

}
