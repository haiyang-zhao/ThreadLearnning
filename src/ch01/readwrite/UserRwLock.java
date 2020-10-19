package ch01.readwrite;

import tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserRwLock implements GoodsService {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock();//读锁
    private final Lock setLock = lock.writeLock();//写锁

    private GoodsInfo goodsInfo;

    public UserRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getInfo() {
        try {
            getLock.lock();
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            getLock.unlock();
        }
    }

    @Override
    public void setNum(int number) {
        try {
            setLock.lock();
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        } finally {
            setLock.unlock();
        }
    }
}
