package com.redsun.classload;

import org.junit.Test;

// 1.系统为配、Price的两个类变量分配内存空间
// 2.按初始化代码(定义时指定初始化值和初始化块中执行初始值)的排列顺序对类变量执行初始化
/*
 * 初始化第一阶段，系统先为INSTANCE、iniPrice两个类变量分配内存空间，此时INSTANCE、iniPrice的值为默认值null和0.0，接着初始化进入
 * 第二阶段，程序按顺序依次为INSTANCE、initPrice进行赋值。对INSTANCE赋值时要调用Price(2.8),创建Price实例，此时立即执行程序中粗体字代码为currentPrice进行赋值，此时initPrice类变量的值为0，因此赋值
 * 的结果是currentPrice等于-2.8，接着，程序再次将initPrice赋为20，但此时对INSTANCE的currentPrice实例变量已经不起作用了
 * 
 * 当Price类初始化完成后，INSTANCE类比那里引用到一个currentPrice为-2.8的Price实例，而initPrice类变量的值为20.0。当再次创建Price实例时，该Price实例的currentPrice实例变量的值才等于20.0-discount
 */
public class PriceTest {

    @Test
    public void main() {
        //通过Price的INSTANCE访问currentPrice实例变量
        System.out.println(Price.INSTANCE.currentPrice);
        //显式创建Price实例
        Price p = new Price(2.8);
        //通过显式创建Price实例访问currentPrice实例变量
        System.out.println(p.currentPrice);
    }
}
