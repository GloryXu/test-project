package com.redsun.impl;

import com.redsun.CountI;

public class CountImpl implements CountI {
    @Override
    public void queryCount() {
        System.out.println("查看账户....");
    }

    @Override
    public void updateCount() {
        System.out.println("更新账户....");
    }
}
