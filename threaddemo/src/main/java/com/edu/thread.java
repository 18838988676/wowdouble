package com.edu;

import org.junit.Test;

public class thread {

    private Object lockA = new Object();
    private Object lockB = new Object();
    private Object lockC = new Object();

    @Test
    public void test(){

    }

    class workThread extends  Thread{
        public void run(){


        }
    }

    void work(String word,Object pre , Object suf){
        synchronized (pre){
            synchronized (suf){
                System.out.println(word);

            }
        }
    }
}
