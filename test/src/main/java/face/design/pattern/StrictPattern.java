package face.design.pattern;

/**
 * 严格模式
 */
public class StrictPattern extends BussinessPattern {

    @Override
    protected void runBefore() {
        System.out.println("严格模式开始");
    }

    @Override
    protected void runAfter() {
        System.out.println("严格模式结束");
    }

}
