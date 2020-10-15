package ch01.base.safeend;

public class EndRunnable {
    private static class UseRunnable implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " is running....");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(new UseRunnable(), "Runnable Thread");
        thread.start();
        Thread.sleep(5);
        thread.interrupt();
    }
}
