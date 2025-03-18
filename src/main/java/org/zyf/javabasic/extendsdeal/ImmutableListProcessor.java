package org.zyf.javabasic.extendsdeal;

import org.assertj.core.util.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 返回不可变集合，改变了父类契约
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:10
 **/
public class ImmutableListProcessor extends ListProcessor{
    @Override
    public List<String> process() {
        // ❌ 返回不可变集合，改变了父类契约
        return Collections.unmodifiableList(Lists.newArrayList("V", "Y", "U"));
    }
}
