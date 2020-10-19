package ch01.readwrite;

public class GoodsInfo {
    private final String name;
    private double totalMoney;//总销售额
    private int storedNumber;//库存数

    public GoodsInfo(String name, double totalMoney, int storedNumber) {
        this.name = name;
        this.totalMoney = totalMoney;
        this.storedNumber = storedNumber;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public int getStoredNumber() {
        return storedNumber;
    }

    public void changeNumber(int sellNumber) {
        this.totalMoney = sellNumber + 25;
        this.storedNumber = sellNumber;
    }
}
