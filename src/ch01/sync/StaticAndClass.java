package ch01.sync;

import tools.SleepTools;

/**
 * 类锁和锁static变量也是不同的
 */
public class StaticAndClass {

    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName()
                    + ":SynClass is running...");
            synClass();
        }
    }

    private static synchronized void synClass() {
        System.out.println(Thread.currentThread().getName()
                + ":synClass going...");
        SleepTools.second(1);
        System.out.println(Thread.currentThread().getName()
                + ":synClass end");
    }

    private static class SynStatic extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName()
                    + ":SynStatic is running...");
            synStatic();
        }
    }

    private static Object obj = new Object();

    private static void synStatic() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName()
                    + ":synStatic going...");
            SleepTools.second(1);
            System.out.println(Thread.currentThread().getName()
                    + ":synStatic end");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new SynStatic();
//        Thread t2 = new SynStatic();
        Thread t2 = new SynStatic();
        t2.start();
        t1.start();
        SleepTools.second(1);
    }
}
