package ch01.volat;

import tools.SleepTools;

/**
 * 演示Volatile的提供的可见性
 */
public class VolatileCase {
    private static volatile boolean ready;
    private static int number;

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread is running...");
            while (!ready) ;
            System.out.println("number = " + number);
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        SleepTools.second(1);
        number = 100;
        ready = true;
        SleepTools.second(5);
        System.out.println("main is end...");
    }
}
