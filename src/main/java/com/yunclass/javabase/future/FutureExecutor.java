package com.yunclass.javabase.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * @author: <a href="http://www.panaihua.com">panaihua</a>
 * @date: 2017年03月23日 13:50
 * @descript:
 * @version: 1.0
 */
public class FutureExecutor implements FutureExecutable {

    private static final Logger LOG = LoggerFactory.getLogger(FutureExecutor.class);

    private ThreadPoolExecutor executorService = null;

    private String executorName;

    public FutureExecutor(String executorName, int corePoolSize, int maxPoolSize, int queueCapacity) {
        this.executorName = executorName;
        executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 10L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueCapacity),
                Executors.defaultThreadFactory());

    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> TimeLimitFuture<V> submit(long timeoutMs,
                                         final Callable<V> callable) {

        final DefaultTimeLimitFuture<V> future = new DefaultTimeLimitFuture<V>(timeoutMs);
        Future<V> delegateFuture = null;
        try {
            future.onSubmit(System.currentTimeMillis());
            LOG.info("开始执行，当前任务总是: {}", executorService.getTaskCount());
            delegateFuture = executorService.submit(new Callable<V>() {

                @Override
                public V call() throws Exception {

                    future.onRuning(System.currentTimeMillis());
                    V result = callable.call();
                    future.onDone(System.currentTimeMillis());
                    return result;
                }
            });

            future.setDelegate(delegateFuture);
            LOG.info("执行完成,总耗时: {}", future.recordTimeFormRuning());

        } catch (Exception e) {
            LOG.error("Failed to submit future task: {}", e.getClass().getCanonicalName());
            return DefaultTimeLimitFuture.REJECTED_FUTURE;
        }

        return future;
    }

    /**
     * 销毁线程池
     */
    @PreDestroy
    public void shutDown() {

        LOG.warn(this.executorName + " is trying to shutdown ... begin");
        if (null != executorService) {
            executorService.shutdown();
        }
        LOG.warn(this.executorName + " is shutdown ... end");
    }
}
