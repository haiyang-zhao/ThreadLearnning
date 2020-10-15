package ch01.base;

/**
 * 新启线程的方式
 */
public class NewThread {
    /*扩展自Thread类*/
    static class UseThread extends Thread {

        @Override
        public void run() {
            super.run();
            System.out.println("i am from extended thread.");
        }
    }

    /*实现Runnable接口*/
    static class UseRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("i'm from runnable impl.");
        }
    }

    public static void main(String[] args) {
        UseThread useThread = new UseThread();
        useThread.start();
        new Thread(new UseRunnable()).start();
    }
}
