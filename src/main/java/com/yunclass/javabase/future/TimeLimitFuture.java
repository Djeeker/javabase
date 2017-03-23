package com.yunclass.javabase.future;

/**
 * @author: <a href="http://www.panaihua.com">panaihua</a>
 * @date: 2017年03月23日 13:48
 * @descript:
 * @version: 1.0
 */
public interface TimeLimitFuture<V> extends TimeRecord {

    /**
     * 给定超时时间，以同步阻塞方式，获取返回结果，
     * 成功结果为非Null值, 如果超出等待时间，或者如果等待过程中抛出异常，将直接返回Null
     *
     * @param timeout 单位：ms
     * @return
     */
    V get(long timeout);

    /**
     * 给定默认的超时时间限制，以同步阻塞方式，获取返回结果
     * 成功结果为非Null值, 如果超出等待时间，或者如果等待过程中抛出异常，将直接返回Null
     *
     * @return
     */
    V get();

    enum Status {

        /**
         * 未执行状态
         */
        PENDING,
        /**
         * 运行中
         */
        RUNNING,
        /**
         * 成功执行完成
         */
        SUCCESS,
        /**
         * 非成功执行完成，非成功执行完成的，都用该状态表示
         */
        NON_SUCCESS;
    }
}
