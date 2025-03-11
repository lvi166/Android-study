package com.cariad.astudy.javas;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadC {

    private static final String TAG = "ThreadC";

    /**
     * 1.CorePoolSize 核心线程数 默认情况下核心线程一直存活，但是将 allowCoreThreadTimeout 设置为True时候，核心线程
     * 会被超时回收
     * <p>
     * 2.maximumPoolSize 线程池最大容量，当活跃线程超过该数值之后，后续新的任务将会阻塞
     * <p>
     * 3.keepAliveTime 线程池中线程的超时时长，如果非核心线程存在时间超过这个时间，则会被超时回收
     * <p>
     * 4.keepAliveTime 的时间单位，可以为TimeUnit.MILLISECONDS TimeUnit.SECONDS TimeUnit.MINUTES
     * <p>
     * 5.workQueue 任务队列，通过线程池的 execute()方法将 Runnable对象存储到该队列中
     * <p>
     * 6.ThreadFactory 线程工厂
     * <p>
     * 7.Handler 拒绝策略，当达到最大线程数时需要执行的饱和策略
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

    public final static int CORE_POOL_SIZE = 2;
    public final static int MAXIMUM_POOL_SIZE = 128;
    public final static int KEEP_ALIVE_TIME = 60;


    ThreadPoolExecutor threadPool;

    public void pool() {

        threadPool = new ThreadPoolExecutor(3, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, getWorkQueue(0));

    }

    /**
     * 任务队列都是实现与 BlockingQueue，及采用了生产者消费者模式
     * <p>
     * ArrayBlockingQueue 一个由数组结构组成的有界阻塞队列
     * linkedBlockingQueue() 一个由链表结构组成的有界阻塞队列，默认大小为 Integer.MAX_VALUE
     * priorityBlockingQueue()
     *
     * @param position
     * @return
     */

    public BlockingQueue getWorkQueue(int position) {




        if (position == 0) {
            return new ArrayBlockingQueue<>(128);
        } else if (position == 1) {
            return new LinkedBlockingQueue();
        } else if (position == 2) {
            return new PriorityBlockingQueue();
        } else if (position == 3) {
            return new DelayQueue();
        } else if (position == 4) {
            return new SynchronousQueue();
        } else if (position == 5) {
            return new LinkedBlockingDeque();
        } else if (position == 6) {
            return new LinkedTransferQueue();
        }


        return null;
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "=>" + Integer.MAX_VALUE);


    }


}
