package ch01.waitnotify;

import tools.SleepTools;

import java.util.Queue;
import java.util.Random;

/**
 * 生产者
 */
public class Producer implements Runnable {

    private final Queue<Integer> queue;
    private final int maxSize;

    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() >= maxSize) {
                    System.out.println("队列已经满了，请等待...");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("生产： " + i);
                queue.add(i);
                queue.notifyAll();
                SleepTools.second(1);
            }
        }
    }
}
