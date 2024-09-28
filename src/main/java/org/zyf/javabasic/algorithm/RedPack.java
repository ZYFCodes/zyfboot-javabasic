package org.zyf.javabasic.algorithm;

/**
 * @program: zyfboot-javabasic
 * @description: 实现一个类似的“二倍均值法”来分配红包
 * @author: zhangyanfeng
 * @create: 2024-09-19 10:31
 **/
public class RedPack {
    private double surplusAmount; // 剩余金额
    private int surplusTotal; // 红包剩余个数

    public RedPack(double surplusAmount, int surplusTotal) {
        this.surplusAmount = surplusAmount;
        this.surplusTotal = surplusTotal;
    }

    public double getSurplusAmount() {
        return surplusAmount;
    }

    public int getSurplusTotal() {
        return surplusTotal;
    }

    public void setSurplusAmount(double surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public void setSurplusTotal(int surplusTotal) {
        this.surplusTotal = surplusTotal;
    }
}
