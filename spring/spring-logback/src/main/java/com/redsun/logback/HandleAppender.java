package com.redsun.logback;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.status.ErrorStatus;

/**
 * @author xuguangrong
 * @description 自定义handler appender
 * @date Created at 16:17 2019/6/8
 */
public class HandleAppender<E> extends AppenderBase<E> {

    private MyPolicy myPolicy;

    private Layout<E> layout;

    public void start() {
        super.start();
        if (this.layout == null) {
            this.addStatus(new ErrorStatus("No layout set for the appender named \"" + this.name + "\".", this));
        }
    }

    public void stop() {
        super.stop();
    }

    @Override
    protected void append(E eventObject) {
        if (this.myPolicy != null) {
            this.myPolicy.handler(this.layout.doLayout(eventObject));
        }
    }

    public MyPolicy getMyPolicy() {
        return myPolicy;
    }

    public void setMyPolicy(MyPolicy myPolicy) {
        this.myPolicy = myPolicy;
    }

    public Layout<E> getLayout() {
        return layout;
    }

    public void setLayout(Layout<E> layout) {
        this.layout = layout;
    }
}
