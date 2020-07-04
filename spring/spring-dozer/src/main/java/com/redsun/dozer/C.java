package com.redsun.dozer;

import java.util.List;

/**
 * @author qiquan
 * @date 2020/06/19 14:09
 */
public class C {

    private B b;

    private String aa;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    private String a;

    private List<String> aList;

    private Integer inter;

    public Integer getInter() {
        return inter;
    }

    public void setInter(Integer inter) {
        this.inter = inter;
    }

    @Override
    public String toString() {
        return "C{" +
                "b=" + b +
                ", aa='" + aa + '\'' +
                ", a='" + a + '\'' +
                ", aList=" + aList +
                ", inter=" + inter +
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
