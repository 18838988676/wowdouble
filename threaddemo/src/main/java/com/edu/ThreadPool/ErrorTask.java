package com.edu.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ErrorTask implements Runnable{
    private double a ;
    private double b ;

    public ErrorTask(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        try{
            double c = a/b ;
            System.out.println(c);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i <5 ; i++) {
            ErrorTask errorTask = new ErrorTask(i+2,i);
            executorService.execute(errorTask);
        }
    }
}
