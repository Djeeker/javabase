package com.yunclass.javabase.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jeeker on 17/1/23.
 */
public class CountDownLatchLearn {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String args[]){

        new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println("第一个任务");
                countDownLatch.countDown();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("第二个任务");
                countDownLatch.countDown();
            }
        }).start();

        try {
            //await 会等待所有线程都做完才会继续执行
            countDownLatch.await();
            System.out.println("全部做完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
