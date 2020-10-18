package ch01.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;
        Producer producer = new Producer(queue, maxSize);
        Consumer consumer = new Consumer(queue, maxSize);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
