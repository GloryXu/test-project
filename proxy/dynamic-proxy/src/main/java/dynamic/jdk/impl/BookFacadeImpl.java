package dynamic.jdk.impl;

import dynamic.jdk.BookFacade;

public class BookFacadeImpl implements BookFacade {

    @Override
    public void addBook() {
        System.out.println("增加图书...");
    }

}
