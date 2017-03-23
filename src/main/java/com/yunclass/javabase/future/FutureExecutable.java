package com.yunclass.javabase.future;

import java.util.concurrent.Callable;

/**
 * @author: <a href="http://www.panaihua.com">panaihua</a>
 * @date: 2017年03月23日 13:48
 * @descript:
 * @version: 1.0
 */
public interface FutureExecutable {

    /**
     * 提交异步任务 返回 {@link TimeLimitFuture}
     *
     * @param timeoutMs 超时时间限制，单位： ms
     * @param callable  异步任务
     * @return <V> 返回结果，由{@link TimeLimitFuture}代理包装
     * @Exception 异常对象
     */
    <V> TimeLimitFuture<V> submit(long timeoutMs, Callable<V> callable);
}
