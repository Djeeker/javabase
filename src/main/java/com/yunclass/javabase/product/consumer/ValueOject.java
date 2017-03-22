package com.yunclass.javabase.product.consumer;

/**
 * 项目名称：javabase
 *
 * @Description：
 * @Date：2017年02月02日 15:27
 * @Author <a href="http://www.panaihua.com/">jeeker</a>
 * @Version 1.0
 */
public class ValueOject {

    public static String value = "";

    public static void main(String[] args){

        String lock = new String("");
        Product product = new Product(lock);
        Consumer consumer = new Consumer(lock);

        ThreadP threadP = new ThreadP(product);
        ThreadC threadC = new ThreadC(consumer);

        new Thread(threadP).start();
        new Thread(threadC).start();



    }



}

class ThreadP implements Runnable {

    private Product product;

    public ThreadP(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.setValue();
        }
    }
}

class ThreadC implements Runnable {

    private Consumer consumer;

    public ThreadC(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            consumer.getValue();
        }
    }
}

