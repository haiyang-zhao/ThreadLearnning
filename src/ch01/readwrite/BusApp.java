package ch01.readwrite;

import tools.SleepTools;

import java.util.Random;

/**
 * 对商品进行业务的应用
 */
public class BusApp {
    static final int READ_WRITE_RATIO = 10;//读写线程的比例
    static final int MIN_THREAD_COUNT = 3;//最少线程数

    /**
     * 读操作
     */
    private static class GetThread implements Runnable {
        private GoodsService goodsService;

        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                goodsService.getInfo();
            }
            System.out.println(Thread.currentThread().getName() + "读取商品数据耗时: " +
                    (System.currentTimeMillis() - start) + "ms");
        }
    }

    private static class SetThread implements Runnable {
        private GoodsService goodsService;

        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random r = new Random();
            for (int i = 0; i < 10; i++) {//操作10次
                SleepTools.ms(50);
                goodsService.setNum(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName()
                    + "写商品数据耗时：" + (System.currentTimeMillis() - start) + "ms---------");
        }
    }

    public static void main(String[] args) {
        GoodsInfo goodsInfo = new GoodsInfo("Cup", 100000, 10000);
        GoodsService goodsService = new UserRwLock(goodsInfo);
        for (int i = 0; i < MIN_THREAD_COUNT; i++) {
            Thread setT = new Thread(new SetThread(goodsService));
            for (int j = 0; j < READ_WRITE_RATIO; j++) {
                Thread getT = new Thread(new GetThread(goodsService));
                getT.start();
            }
            SleepTools.ms(100);
            setT.start();
        }
    }
}
