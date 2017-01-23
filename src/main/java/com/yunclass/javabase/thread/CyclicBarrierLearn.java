package com.yunclass.javabase.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * 项目名称：javabase
 *
 * @Description： CyclicBarrier 让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有线程才会继续执行
 * @Date：2017年01月23日 18:25
 * @Author <a href="http://www.panaihua.com/">jeeker</a>
 * @Version 1.0
 */
public class CyclicBarrierLearn {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new A());

    public static void main(String[] args){

        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("第一个任务");

            }
        }).start();

        try {
            //插入屏障
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println("任务开始执行");
        }
    }
}
