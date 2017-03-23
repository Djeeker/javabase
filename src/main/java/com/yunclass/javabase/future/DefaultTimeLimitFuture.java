package com.yunclass.javabase.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: <a href="http://www.panaihua.com">panaihua</a>
 * @date: 2017年03月23日 13:45
 * @descript:
 * @version: 1.0
 */
public class DefaultTimeLimitFuture<V> implements TimeLimitFuture<V> {

    private final static Logger LOG = LoggerFactory.getLogger(DefaultTimeLimitFuture.class);

    private Future<V> delegate = null;

    private long timeoutLimitMs = 0l;

    /**
     * 记录运行情况
     */
    private volatile long submitTime = 0l;
    private volatile long runingTime = 0l;
    private volatile long doneTime = 0l;
    private volatile Status status = Status.PENDING;


    public DefaultTimeLimitFuture(long timeoutLimitMs) {
        this.timeoutLimitMs = timeoutLimitMs;
    }

    public DefaultTimeLimitFuture(Status status) {
        this.status = status;
    }

    @SuppressWarnings("rawtypes")
    public static final DefaultTimeLimitFuture REJECTED_FUTURE = new DefaultTimeLimitFuture(Status.NON_SUCCESS);

    @Override
    public V get(long timeout) {

        V result = null;
        if (null == delegate) {
            LOG.warn("Failed for Null of Future");
            return result;
        }

        timeout = timeout < 0 ? 0 : timeout;

        try {
            result = delegate.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            //ignore 计划内超时
            LOG.warn("Get result TimeoutException for executor: {}", e);

        } catch (Exception e) {
            LOG.warn("Get result InterruptedException: {}", e.getClass().getCanonicalName());
            LOG.error(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public V get() {
        return get(timeoutLimitMs - (System.currentTimeMillis() - submitTime));
    }

    public void setDelegate(Future<V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onSubmit(long time) {
        this.submitTime = time;
        status = Status.PENDING;
    }

    @Override
    public void onRuning(long time) {
        this.runingTime = time;
        status = Status.RUNNING;
    }

    @Override
    public void onDone(long time) {
        this.doneTime = time;
        status = Status.SUCCESS;
    }

    @Override
    public long recordTimeFormRuning() {
        return status == Status.SUCCESS ? doneTime - runingTime : 0l;
    }
}
