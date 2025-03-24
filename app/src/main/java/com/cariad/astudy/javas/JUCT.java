package com.cariad.astudy.javas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC 相关
 */
public class JUCT {

    /**
     * synchronized售票例子
     */

    //真正在公司开发，遵守oop思想，降低耦合性
    //线程就是一个单独的资源类，没有任何附属操作，里面只包含属性、方法
    public static void main(String[] args) {
//        //并发：多个线程操作同一个资源
//        Ticket2 ticket = new Ticket2();
//
//        //lambda表达式
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//
//
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//
//            }
//        }, "A").start();
//        new Thread(() -> {
//            for (int i = 0; i < 40; i++) {
//                ticket.sale();
//            }
//        }, "B").start();
//        new Thread(() -> {
//            for (int i = 0; i < 40; i++) {
//                ticket.sale();
//            }
//        }, "C").start();


        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }


    //资源类
    static class Ticket1 {
        //票数
        private int number = 30;

        //买票方法
        //synchronized本质就是队列+锁
        public synchronized void sale() {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "抢到了第" + (number--) + "张票，剩下" + number);
            }
        }

    }


    static class Ticket2 {
        //票数
        private int number = 30;

        //Lock锁
        Lock lock = new ReentrantLock(false);

        //买票方法
        public void sale() {
            lock.lock();//加锁
            try {
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "抢到了第" + (number--) + "张票，剩下" + number);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();//解锁
            }
        }
    }


    //资源类
    static class Data {
        //变量
        private int num = 0;

        //加
        public synchronized void increment() throws InterruptedException {
            //判断等待
            if (num != 0) {
                this.wait();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            //通知其他线程，+1执行完毕
            this.notifyAll();
        }

        //减
        public synchronized void decrement() throws InterruptedException {
            //判断等待
            if (num == 0) {
                this.wait();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            //通知其他线程，-1执行完毕
            this.notifyAll();




        }

        Exception






    }

}

