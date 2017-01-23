package com.yunclass.javabase.thread;

/**
 * Created by jeeker on 17/1/23.
 */
public class ThreadJoinLearn {

    public static void main(String args[]){

        Thread parse1 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("parse1 finish");
            }
        });

        Thread parse2 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("parse2 finish");
            }
        });

        parse1.start();
        parse2.start();

        try {

            //join 会等待线程执行完成在往下执行
            parse1.join();
            parse2.join();
            System.out.println("all finish");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
