package com.demo.pattern.template;

/**
 * <p>
 * 设计模式：模板方法模式
 * </p>
 *
 *
 * <p>
 * 模板方法模式是一种行为设计模式，它定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。
 * 模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤的实现方式。
 * </p>
 *
 * @author developer.wang
 * @date 2020/3/21
 */
public abstract class Template {

    public final void templateMethod() {
        this.primitiveOperation();
        this.primitiveOperation2();
        this.primitiveOperation3();
    }

    protected void primitiveOperation() {
        // 当前类的实现

    }

    // 被子类实现的方法
    protected abstract void primitiveOperation2();

    // 被子类实现的方法
    protected abstract void primitiveOperation3();
}
