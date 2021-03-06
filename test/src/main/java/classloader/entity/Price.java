package classloader.entity;

public class Price {
    // 类成员是Price实例
    public final static Price INSTANCE = new Price(2.8);

    //再定义一个类变量
    public static double initPrice = 20;
    // 定义该Price的currentPrice实例变量
    public double currentPrice;

    public Price(double discount) {
        //根据静态变量计算实例变量
        currentPrice = initPrice - discount;
    }
}
