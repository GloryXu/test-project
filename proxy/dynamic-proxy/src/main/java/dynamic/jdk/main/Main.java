package dynamic.jdk.main;

import dynamic.jdk.BookFacade;
import dynamic.jdk.impl.BookFacadeImpl;
import dynamic.jdk.proxy.BookFacadeProxy;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        BookFacade bookFacadeImpl = new BookFacadeImpl();

        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookfacade = (BookFacade) proxy.bind(bookFacadeImpl);
        bookfacade.addBook();

        System.out.println(bookFacadeImpl == bookfacade);
        System.in.read();
    }
}
