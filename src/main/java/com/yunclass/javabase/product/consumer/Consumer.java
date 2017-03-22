package com.yunclass.javabase.product.consumer;

/**
 * 项目名称：javabase
 *
 * @Description：
 * @Date：2017年02月02日 15:30
 * @Author <a href="http://www.panaihua.com/">jeeker</a>
 * @Version 1.0
 */
public class Consumer {

    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public void getValue(){

        try {

            synchronized (lock) {

                if (ValueOject.value.equals("")){
                    lock.wait();
                }

                System.out.println("get的值是：" + ValueOject.value);
                ValueOject.value = "";
                lock.notify();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
