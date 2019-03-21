package face.design.pattern;

/**
 * 使用模板模式实现
 *
 * 严格和不严格使用sequence实现调用顺序，并支持各自模式的前后不用处理逻辑分隔开
 */
public class Main {
    public static void main(String[] args) {
        PatternFactory patternFactory = new PatternFactory();

        patternFactory.getNotStrictPattern().run();

        System.out.println();

        patternFactory.getStrictPattern().run();
    }
}
