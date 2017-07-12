package com.hframework.task;

import net.sf.ehcache.util.NamedThreadFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangquanhong on 2016/9/26.
 */
public class SchedulerFactory {

    private static final int DEFAULT_POOL_SIZE = 10;
    private static final long DEFAULT_PERIOD = 3*1000;

    private static final ScheduledThreadPoolExecutor defaultScheduler = new ScheduledThreadPoolExecutor(DEFAULT_POOL_SIZE, new NamedThreadFactory("[job-thread-group]"));

    private static Map<Job, ScheduledFuture> jobs = new HashMap<Job, ScheduledFuture>();

    public static void register(Job job) {
        register(job, DEFAULT_PERIOD, DEFAULT_PERIOD);
    }

    public static void register(final Job job, long initialDelay, long period) {
        ScheduledFuture<?> scheduledFuture = defaultScheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                job.execute();
            }
        }, initialDelay, period, TimeUnit.MILLISECONDS);
        jobs.put(job, scheduledFuture);
    }

    public static void unregisterNow(Job job){
        jobs.get(job).cancel(true);
    }

    public static void main(String[] args) throws InterruptedException {
        Job job = new Job() {
            public void execute() {
                System.out.println(Thread.currentThread().getName() + ": do your business !" );
            }
        };
        SchedulerFactory.register(job);
        Thread.sleep(1000 * 10L);
        SchedulerFactory.unregisterNow(job);
    }
}
