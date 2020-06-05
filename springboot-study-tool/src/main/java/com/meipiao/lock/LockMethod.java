package com.meipiao.lock;

import org.springframework.scheduling.support.SimpleTriggerContext;

/**
 * @Author: Chenwx
 * @Date: 2020/6/5 14:05
 */
public class LockMethod {
    public synchronized void syncMethod(){
        long start = System.currentTimeMillis();
        getOne();
        getTwo();
        getFive();
        long end = System.currentTimeMillis();
        System.err.println("user time is "+ (end -start));
    }


    public  void syncMethod2(){
        long start = System.currentTimeMillis();
        getOne();
        synchronized (this){
        getTwo();
        }
        getFive();
        long end = System.currentTimeMillis();
        System.err.println("user time is "+ (end -start));
    }

    public String getOne(){
        return "one";
    }

    public static void main(String[] args) {
        LockMethod lockMethod = new LockMethod();
        lockMethod.syncMethod();
        lockMethod.syncMethod2();

    }

    public String getTwo(){
        return "two";
    }

    public String getFive(){
        return "five";
    }
}
