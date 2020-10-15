package ch01.sync;

/**
 * synchronized关键字的使用方法
 */
public class SyncTest {
    private long count = 0;
    private final Object obj = new Object();//作为一个锁
    public long getCount() {
        return count;
    }

    /*用在方法上*/
    public synchronized void add() {
        count++;
    }

    /*用在同步块上，但是锁的是当前类的对象实例*/
    public void add1() {
        synchronized (this) {
            count++;
        }
    }

    public void add2() {
        synchronized (obj) {
            count++;
        }
    }

    private static class Count extends Thread {
        private final SyncTest syncTest;

        public Count(SyncTest syncTest) {
            this.syncTest = syncTest;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
//                this.syncTest.add();
//                this.syncTest.add1();
                this.syncTest.add2();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();
        Count count1 = new Count(syncTest);
        Count count2 = new Count(syncTest);
        count1.start();
        count2.start();
        Thread.sleep(5000);
        System.out.println(syncTest.getCount());
    }
}
