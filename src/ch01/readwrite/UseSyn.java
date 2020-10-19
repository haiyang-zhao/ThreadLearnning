package ch01.readwrite;

import tools.SleepTools;

/**
 * 用内置锁来实现商品服务接口
 */
public class UseSyn implements GoodsService {


    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getInfo() {
        SleepTools.ms(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);
    }
}
