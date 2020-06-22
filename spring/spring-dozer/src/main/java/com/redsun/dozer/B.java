package com.redsun.dozer;

import java.util.List;

/**
 * @author qiquan
 * @date 2020/06/19 14:07
 */
public class B {

    private String b;

    private List<String> aList;

    @Override
    public String toString() {
        return "B{" +
                "b='" + b + '\'' +
                ", bList=" + aList +
                '}';
    }

    public List<String> getaList() {
        return aList;
    }

    public void setaList(List<String> aList) {
        this.aList = aList;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
