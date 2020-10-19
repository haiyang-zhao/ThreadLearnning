package ch01.deadlock;

import tools.SleepTools;

/**
 * 演示死锁的产生
 */
public class NormalDeadLock {
    private static final Object No13 = new Object();
    private static final Object No14 = new Object();

    private static void zhangDo() {
        String threadName = Thread.currentThread().getName();
        synchronized (No13) {
            System.out.println(threadName + " get No13");
            SleepTools.ms(100);
            synchronized (No14) {
                System.out.println(threadName + " get No14");
            }
        }

    }

    private static void liDo() {
        String threadName = Thread.currentThread().getName();
        synchronized (No13) {
            System.out.println(threadName + " get No13");
            SleepTools.ms(100);
            synchronized (No14) {
                System.out.println(threadName + " get No14");
            }
        }
    }

    private static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            zhangDo();
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread("Zhangsan..");
        myThread.start();
        Thread.currentThread().setName("Lisi..");
        liDo();
    }
}
