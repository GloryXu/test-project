package atomic;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInt {

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;// 注意是静态的

    private volatile int value;
    public final int get() {
        return value;
    }

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));// 反射出value属性，获取其在内存中的位置
        } catch (Exception ex) { throw new Error(ex); }
    }

    public final int getAndIncrement() {
        for (;;) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next))
                return current;
        }
    }

    public final boolean compareAndSet(int expect, int update) {
        //Unsafe类提供的硬件级别的compareAndSwapInt方法;
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
}
