package ch01.threadlocal;

/**
 * ThreadLocal基本用法
 */
public class UseThreadLocal {

    private static final ThreadLocal<Integer> threadLocals =
            ThreadLocal.withInitial(() -> 1);


    static class MyThread extends Thread {
        private int id;

        public MyThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            Integer s = threadLocals.get();
            s = s + id;
            threadLocals.set(s);
            System.out.println(Thread.currentThread().getName() + ":" + threadLocals.get());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new MyThread(i).start();
        }
    }
}
