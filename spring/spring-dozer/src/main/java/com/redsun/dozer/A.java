package com.redsun.dozer;

import java.util.List;

/**
 * @author qiquan
 * @date 2020/06/19 14:06
 */
public class A extends AA {

    private B b;

    private String a;

    private List<String> aList;

    private String inter;

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + b +
                ", a='" + a + '\'' +
                ", aList=" + aList +
                ", inter='" + inter + '\'' +
                '}';
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<String> getaList() {
        return aList;
    }

    public void setaList(List<String> aList) {
        this.aList = aList;
    }
}
