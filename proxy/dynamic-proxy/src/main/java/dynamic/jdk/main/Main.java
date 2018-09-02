package dynamic.jdk.main;

import dynamic.jdk.BookFacade;
import dynamic.jdk.impl.BookFacadeImpl;
import dynamic.jdk.proxy.BookFacadeProxy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BookFacade bookFacadeImpl = new BookFacadeImpl();

        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookfacade = (BookFacade) proxy.bind(bookFacadeImpl);
        bookfacade.addBook();

        System.out.println(bookFacadeImpl == bookfacade);
        Thread.sleep(1000000);
    }
}
