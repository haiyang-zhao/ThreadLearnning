package ch01.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class UseAtomicInteger {
    static AtomicInteger a = new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(a.getAndIncrement()); //以原子方式将当前值加1，注意，这里返回的是自增前的值。
        System.out.println(a.incrementAndGet());
        System.out.println(a.getAndAdd(24));//以原子方式设置为newValue的值，并返回旧值。
        System.out.println(a.addAndGet(24));
    }
}
