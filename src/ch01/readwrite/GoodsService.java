package ch01.readwrite;

/**
 * 商品的服务的接口
 */
public interface GoodsService {
    GoodsInfo getInfo();//获得商品的信息

    void setNum(int number);//设置商品的数量
}
