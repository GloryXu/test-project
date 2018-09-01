package com.redsun.proxy;

import com.redsun.CountI;

public class CountProxy implements CountI {

    private CountI countImpl;

    public CountProxy(CountI countImpl) {
        this.countImpl = countImpl;
    }

    @Override
    public void queryCount() {
        countImpl.queryCount();
    }

    @Override
    public void updateCount() {
        countImpl.updateCount();
    }
}
