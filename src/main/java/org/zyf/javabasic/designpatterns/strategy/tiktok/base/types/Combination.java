package org.zyf.javabasic.designpatterns.strategy.tiktok.base.types;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;

/**
 * @program: zyfboot-javabasic
 * @description: 枚举组合类
 * @author: zhangyanfeng
 * @create: 2025-11-02 15:29
 **/
public class Combination {
    private AType a;
    private BType b;
    private CType c;
    private DType d;


    public Combination(AType a, BType b, CType c, DType d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


    public AType getA() {
        return a;
    }

    public BType getB() {
        return b;
    }

    public CType getC() {
        return c;
    }

    public DType getD() {
        return d;
    }


    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", a, b, c, d);
    }
}
