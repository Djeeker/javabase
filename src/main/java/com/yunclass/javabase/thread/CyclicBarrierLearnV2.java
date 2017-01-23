package com.yunclass.javabase.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * 项目名称：javabase
 *
 * @Description： <ul>
 *                  <li>场景：计算两个任务执行之后的总和</li>
 *                  <li>与 countDownLatch 区别：CyclicBarrier可以reset()方法重置，所以可以处理更加复杂的情况，比如计算发生错误时重新计算/li>
 *                </ul>
 * @Date：2017年01月23日 20:16
 * @Author <a href="http://www.panaihua.com/">jeeker</a>
 * @Version 1.0
 */
public class CyclicBarrierLearnV2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new ThreadC());

    private static ConcurrentHashMap<String,Integer> countMap = new ConcurrentHashMap();

    public static void main(String[] args){

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // A 线程对某个任务计算得到的结果
                countMap.put(Thread.currentThread().getName(),1);
                System.out.println("A 线程计算完成");
                try {
                    //插入屏障
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                // B 线程对某个任务计算得到的结果
                countMap.put(Thread.currentThread().getName(),3);
                System.out.println("B 线程计算完成");
                try {
                    //插入屏障
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //没有先后顺序，但是一定会在ThreadC之前执行
        threadA.start();
        threadB.start();
    }

    /**
     * 在最后执行
     */
    static class ThreadC implements Runnable {

        @Override
        public void run() {

            int result = 0;
            for (Map.Entry<String,Integer> count : countMap.entrySet()){
                result += count.getValue();
            }

            System.out.println("结果之和为" + result);
        }
    }
}
