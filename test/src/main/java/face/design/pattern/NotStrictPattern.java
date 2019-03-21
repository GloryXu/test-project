package face.design.pattern;

/**
 * 不严格模式
 */
public class NotStrictPattern extends BussinessPattern {

    @Override
    protected void runBefore() {
        System.out.println("不严格模式开始");
    }

    @Override
    protected void runAfter() {
        System.out.println("不严格模式结束");
    }
}
