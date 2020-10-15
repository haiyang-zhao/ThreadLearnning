package ch01.base.safeend;

public class EndThread {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " interrupt flag =" + isInterrupted());
            while (!Thread.interrupted()){//outer interrupt flag 会是true
//            while (!isInterrupted()) {
                System.out.println(threadName + " is running");
                System.out.println(threadName + " inner interrupt flag ="
                        + isInterrupted());
            }
            System.out.println(threadName + " outer interrupt flag =" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final UseThread useThread = new UseThread("MyThread");
        useThread.start();
        Thread.sleep(5);
        useThread.interrupt();

    }
}
