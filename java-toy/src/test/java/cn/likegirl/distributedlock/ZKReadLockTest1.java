package cn.likegirl.distributedlock;

import cn.likegirl.zookeeper.locks.distributedlock.ZKReadWriteLock1;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Created by code4wt on 17/8/27.
 */
public class ZKReadLockTest1 {

    @Test
    public void lock() throws Exception {
        Runnable runnable = () -> {
            try {
                ZKReadWriteLock1 srwl = new ZKReadWriteLock1();
                srwl.readLock().lock();
                Thread.sleep(1000 + new Random(System.nanoTime()).nextInt(2000));
                srwl.readLock().unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        int poolSize = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Thread.sleep(10);
            executorService.submit(runnable);
        }

        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void tryLock() throws Exception {
        ZKReadWriteLock1 srwl = new ZKReadWriteLock1();
        Boolean locked = srwl.readLock().tryLock();
        System.out.println("locked: " + locked);
        srwl.readLock().unlock();
    }

    @Test
    public void tryLock1() throws Exception {
        ZKReadWriteLock1 srwl = new ZKReadWriteLock1();
        Boolean locked = srwl.readLock().tryLock(20000);
        System.out.println("locked: " + locked);
        srwl.readLock().unlock();
    }

    @Test
    public void unlock() throws Exception {
    }
}