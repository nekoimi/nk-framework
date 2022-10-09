package com.sakuraio.nk.util;

/**
 * <p>StepJump</p>
 *
 * @author nekoimi 2022/10/09
 */
public class StepJump {
    /**
     * <p>初始值</p>
     */
    private int value = 0;

    /**
     * <p>步长</p>
     */
    private final int step;

    public StepJump(int value, int step) {
        this.value = value;
        this.step = step;
    }

    /**
     * <p>创建对象</p>
     *
     * @param value 初始值
     * @param step  步长
     * @return
     */
    public static StepJump create(int value, int step) {
        return new StepJump(value, step);
    }

    /**
     * <p>上一步->值</p>
     *
     * @return
     */
    public int prev() {
        int prev = this.value;
        this.value -= this.step;
        return prev;
    }

    /**
     * <p>下一步->值</p>
     *
     * @return
     */
    public int next() {
        int next = this.value;
        this.value += this.step;
        return next;
    }

    @Override
    public String toString() {
        return "StepJump{" +
                "value=" + value +
                ", step=" + step +
                '}';
    }
}
