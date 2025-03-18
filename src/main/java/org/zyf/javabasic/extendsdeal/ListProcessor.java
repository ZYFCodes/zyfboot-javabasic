package org.zyf.javabasic.extendsdeal;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 返回可变集合，调用方默认可以修改
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:09
 **/
public class ListProcessor {
    public List<String> process() {
        // 返回可变集合，调用方默认可以修改
        return new ArrayList<>(Lists.newArrayList("A", "B", "C"));
    }
}
