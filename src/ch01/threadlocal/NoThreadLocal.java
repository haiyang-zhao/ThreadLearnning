package ch01.threadlocal;

public class NoThreadLocal {
    static Integer count = 1;

    static class MyThread extends Thread {
        int id;

        public MyThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : start");
            count = count + this.id;
            System.out.println(Thread.currentThread().getName() + " : " + count);

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new MyThread(i).start();
        }
    }
}
