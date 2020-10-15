package ch01.sync;

import tools.SleepTools;

/**
 * 锁的实例不一样，也是可以并行的
 */
public class DiffObject {

    static class ObjectSync1 implements Runnable {

        private DiffObject diffObject;

        public ObjectSync1(DiffObject diffObject) {
            this.diffObject = diffObject;
        }

        @Override
        public void run() {
            System.out.println("ObjectSync1 is running... " + diffObject);
            this.diffObject.instance();
        }
    }

    static class ObjectSync2 implements Runnable {
        private DiffObject diffObject;

        public ObjectSync2(DiffObject diffObject) {
            this.diffObject = diffObject;
        }

        @Override
        public void run() {
            System.out.println("ObjectSync2 is running... " + diffObject);
            this.diffObject.instance1();
        }
    }

    private synchronized void instance() {
        SleepTools.second(3);
        System.out.println(this + " instance() running..");
        SleepTools.second(3);
        System.out.println(this + " instance() run ended.");

    }

    private synchronized void instance1() {
        SleepTools.second(3);
        System.out.println(this + " instance1() running..");
        SleepTools.second(3);
        System.out.println(this + "instance1() run ended.");
    }

    public static void main(String[] args) {
        final DiffObject object = new DiffObject();
        final DiffObject object1 = new DiffObject();
        final Thread t1 = new Thread(new ObjectSync1(object));
        final Thread t2 = new Thread(new ObjectSync2(object1));
        t1.start();
        t2.start();
        SleepTools.second(4);
    }
}
