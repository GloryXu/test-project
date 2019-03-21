package face.design.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 校验模式工厂
 *
 * 如果并发高，频繁的创建对象不合适，也可以将各个pattern创建好，存入map集合中
 * 每个获取时，从map中获取直接返回即可（加载什么类型的pattern，也可以实现配置化，如db、redis、properties文件都可）
 * @author xuguangrong
 */
public class PatternFactory {

    public BussinessPattern getStrictPattern() {
        BussinessPattern strictPattern = new StrictPattern();
        // 此处的顺序暂时写死，也可以实现配置化，这样更灵活
        List<String> strictSequence = new ArrayList<>();
        strictSequence.add("A");
        strictSequence.add("B");
        strictSequence.add("C");
        strictPattern.setSequence(strictSequence);

        return strictPattern;
    }

    public BussinessPattern getNotStrictPattern() {
        BussinessPattern notStrictPattern = new NotStrictPattern();
        List<String> notstrictSequence = new ArrayList<>();
        notstrictSequence.add("A");
        notstrictSequence.add("B");
        notStrictPattern.setSequence(notstrictSequence);

        return notStrictPattern;
    }
}
