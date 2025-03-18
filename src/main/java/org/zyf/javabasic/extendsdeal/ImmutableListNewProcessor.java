package org.zyf.javabasic.extendsdeal;

import java.util.Collections;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 使用组合模式对原类封装
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:21
 **/
public class ImmutableListNewProcessor {
    private final ListProcessor delegate;

    public ImmutableListNewProcessor(ListProcessor delegate) {
        this.delegate = delegate;
    }

    public List<String> process() {
        List<String> raw = delegate.process();
        return Collections.unmodifiableList(raw); // ✅ 明确行为由组合控制
    }
}
