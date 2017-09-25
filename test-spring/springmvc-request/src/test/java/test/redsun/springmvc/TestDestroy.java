package test.redsun.springmvc;

import javax.servlet.http.HttpServlet;

public class TestDestroy extends HttpServlet {

    @Override
    public void destroy() {
        System.out.println("TestDestroy is destroyed!!");
        super.destroy();
    }


}
