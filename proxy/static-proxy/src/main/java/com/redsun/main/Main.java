package com.redsun.main;

import com.redsun.CountI;
import com.redsun.impl.CountImpl;
import com.redsun.proxy.CountProxy;

public class Main {

    public static void main(String[] args) {
        CountI countImpl = new CountImpl();

        CountI countProxy = new CountProxy(countImpl);

        countProxy.queryCount();
        countProxy.updateCount();
    }
}
