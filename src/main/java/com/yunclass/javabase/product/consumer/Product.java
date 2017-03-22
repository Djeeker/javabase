package com.yunclass.javabase.product.consumer;

/**
 * 项目名称：javabase
 *
 * @Description： 生成者
 * @Date：2017年02月02日 15:16
 * @Author <a href="http://www.panaihua.com/">jeeker</a>
 * @Version 1.0
 */
public class Product {

    private String lock;

    public Product(String lock) {
        this.lock = lock;
    }

    public void setValue(){

        try {

            synchronized (lock) {

                if (!ValueOject.value.equals("")){
                    lock.wait();
                }

                String value = System.currentTimeMillis() + "_";
                System.out.print("set的值是：" + value);
                ValueOject.value = value;
                lock.notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
