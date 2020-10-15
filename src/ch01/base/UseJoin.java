package ch01.base;

import tools.SleepTools;

public class UseJoin {
    private static class Goddess implements Runnable {
        private Thread thread;

        public Goddess(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            System.out.println("Goddess开始排队打饭.....");
            try {
                if (thread != null) {
                    thread.join();
                }
            } catch (InterruptedException e) {
            }
            SleepTools.second(2);//休眠2秒
            System.out.println(Thread.currentThread().getName() + " Goddess打饭完成.");
        }
    }

    static class GoddessBoyfriend implements Runnable {
        public void run() {
            SleepTools.second(2);//休眠2秒
            System.out.println("GoddessBoyfriend开始排队打饭.....");
            System.out.println(Thread.currentThread().getName() + " GoddessBoyfriend打饭完成.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final GoddessBoyfriend gdbRunnable = new GoddessBoyfriend();
        final Thread gdbThread = new Thread(gdbRunnable);

        final Goddess goddess = new Goddess(gdbThread);
        final Thread goddessThread = new Thread(goddess);

        goddessThread.start();
        gdbThread.start();
        System.out.println("lison开始排队打饭.....");
        goddessThread.join();
        SleepTools.second(2);//让主线程休眠2秒
        System.out.println(Thread.currentThread().getName() + " lison打饭完成.");

    }
}
