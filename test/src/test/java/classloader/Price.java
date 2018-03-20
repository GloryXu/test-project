package classloader;

public class Price {
    // 类成员是Price实例
    final static Price INSTANCE = new Price(2.8);

    //再定义一个类变量
    static double initPrice = 20;
    // 定义该Price的currentPrice实例变量
    double currentPrice;

    public Price(double discount) {
        //根据静态变量计算实例变量
        currentPrice = initPrice - discount;
    }
}
