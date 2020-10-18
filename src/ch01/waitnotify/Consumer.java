package ch01.waitnotify;

import tools.SleepTools;

import java.util.Queue;

/**
 * 消费者
 */
public class Consumer implements Runnable {

    private final Queue<Integer> queue;

    private final int maxSize;

    public Consumer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    System.out.println("队列为空，等待生产者。。。");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer poll = queue.remove();
                System.out.println("消费 " + poll);
                queue.notifyAll();
                SleepTools.second(1);
            }
        }

    }
}
