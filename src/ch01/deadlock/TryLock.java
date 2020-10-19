package ch01.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示尝试拿锁解决死锁
 */
public class TryLock {
    private static final Lock No13 = new ReentrantLock();
    private static final Lock No14 = new ReentrantLock();

    /**
     * 先尝试拿No13 锁，再尝试拿No14锁，No14锁没拿到，连同No13 锁一起释放掉
     * @throws InterruptedException
     */
    private static void firstToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random r = new Random();
        while (true) {
            if (No13.tryLock()) {
                System.out.println(threadName + " get No13");
                try {
                    if (No14.tryLock()) {
                        try {
                            System.out.println(threadName + " get No14");
                            System.out.println("first to second do work....");
                            break;
                        } finally {
                            No14.unlock();
                        }
                    }
                } finally {
                    No13.unlock();
                }
            }
            Thread.sleep(r.nextInt(3));
        }
    }

    /**
     * 尝试拿No14锁，再尝试拿No13锁，No13锁没拿到，连同No14锁一起释放掉
     * @throws InterruptedException
     */
    private static void secondToFirst() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random r = new Random();
        while (true) {
            if (No14.tryLock()) {
                System.out.println(threadName + " get No14");
                try {
                    if (No13.tryLock()) {
                        try {
                            System.out.println(threadName + " get No13");
                            System.out.println("second to first do work....");
                            break;
                        } finally {
                            No13.unlock();
                        }
                    }
                } finally {
                    No14.unlock();
                }
            }
            Thread.sleep(r.nextInt(3));
        }
    }

    private static class TestThread extends Thread {

        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        public void run() {
            Thread.currentThread().setName(name);
            try {
                secondToFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("TestDeadLock");
        TestThread testThread = new TestThread("SubTestThread");
        testThread.start();
        try {
            firstToSecond();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
