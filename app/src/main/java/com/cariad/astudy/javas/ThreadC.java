package com.cariad.astudy.javas;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadC {

    /**
     *
     * 1.CorePoolSize 核心线程数 默认情况下核心线程一直存活，但是将 allowCoreThreadTimeout 设置为True时候，核心线程
     * 会被超时回收
     *
     * 2.maximumPoolSize 线程池最大容量，当活跃线程超过该数值之后，后续新的任务将会阻塞
     *
     * 3.keepAliveTime 线程池中线程的超时时长，如果非核心线程存在时间超过这个时间，则会被超时回收
     *
     * 4.keepAliveTime 的时间单位，可以为TimeUnit.MILLISECONDS TimeUnit.SECONDS TimeUnit.MINUTES
     *
     * 5.workQueue 任务队列，通过线程池的 execute()方法将 Runnable对象存储到该队列中
     *
     * 6.ThreadFactory 线程工厂
     *
     *
     *
     *
     *
     */

//    public ThreadPoolExecutor(int corePoolSize,
//                              int maximumPoolSize,
//                              long keepAliveTime,
//                              TimeUnit unit,
//                              BlockingQueue<Runnable> workQueue,
//                              ThreadFactory threadFactory) {
//        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
//                threadFactory, defaultHandler);
//    }



}
