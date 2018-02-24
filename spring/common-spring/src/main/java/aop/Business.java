package aop;

public class Business implements IBusiness,IBusiness2 {
    @Override
    public void doSomeThing() {
        System.out.println("执行业务逻辑");
    }

    @Override
    public void doSomeThing2() {
        System.out.println("执行业务逻辑2");
    }
}
