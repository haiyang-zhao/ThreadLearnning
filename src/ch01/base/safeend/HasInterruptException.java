package ch01.base.safeend;

public class HasInterruptException {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    //当阻塞方法收到中断请求的时候就会抛出InterruptedException异常
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()
                            +" in InterruptedException interrupt flag is "
                            +isInterrupted());
                    //如果继续中断需要再次调用interrupt(),因为在捕获InterruptedException异常的时候自动的将是否请求中断标志置为了false
                    interrupt();
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()
                            + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName()
                        +" interrupt flag is "+isInterrupted());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final UseThread thread = new UseThread("MyThread");
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
