package ch01.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示锁的范式
 */
public class LockDome {
    private int count = 0;
    private Lock lock = new ReentrantLock(true);

    public void incr() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void  incr2(){
        count++;

    }
}
