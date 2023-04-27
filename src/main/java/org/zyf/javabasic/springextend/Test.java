package org.zyf.javabasic.springextend;

import com.google.common.base.Joiner;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/16  23:56
 */
public class Test {
    private static final Joiner JOINER = Joiner.on("_").useForNull("");
    public static void main(String[] args) {
        System.out.println(JOINER.join(1, "auditDataId",45L,"hgfd"));
    }
}
