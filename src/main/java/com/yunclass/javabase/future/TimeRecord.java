package com.yunclass.javabase.future;

/**
 * @author: <a href="http://www.panaihua.com">panaihua</a>
 * @date: 2017年03月23日 13:49
 * @descript:
 * @version: 1.0
 */
interface TimeRecord {

    /**
     * 设置任务提交时间
     */
    void onSubmit(long time);

    /**
     * 设置任务开始时间
     */
    void onRuning(long time);

    /**
     * 设置任务结束时间
     */
    void onDone(long time);

    /**
     * 记录从开始运行到结束的耗时
     */
    long recordTimeFormRuning();
}
