package face.design.pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class BussinessPattern {

    private List<String> sequence = new ArrayList<>();

    /**
     * 运行之前逻辑
     */
    protected abstract void runBefore();

    /**
     * 运行之后逻辑
     */
    protected abstract void runAfter();

    public void run() {
        runBefore();

        //循环一遍，谁在前，就先执行谁
        for (int i = 0; i < this.sequence.size(); i++) {
            String actionName = this.sequence.get(i);
            if (actionName.equalsIgnoreCase("A")) { //如果是start关键字，
                System.out.println("A 条件满足");
            } else if (actionName.equalsIgnoreCase("B")) { //如果是stop关键字
                System.out.println("B 条件满足");
            } else if (actionName.equalsIgnoreCase("C")) { //如果是alarm关键字
                System.out.println("C 条件满足");
            }
        }

        runAfter();
    }

    final public void setSequence(List<String> sequence) {
        this.sequence = sequence;
    }
}
