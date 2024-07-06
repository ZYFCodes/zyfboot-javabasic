package org.zyf.javabasic.letcode.dynamicprogramming.project.base;

import lombok.Data;

/**
 * @program: zyfboot-javabasic
 * @description: Player
 * @author: zhangyanfeng
 * @create: 2022-07-06 22:03
 **/
@Data
public class Player {
    String position;
    /**
     * 签约费用，以10万美元为单位
     */
    int cost;
    /**
     * 球员的VORP值
     */
    int vorp;

    public Player(String position, int cost, int vorp) {
        this.position = position;
        this.cost = cost;
        this.vorp = vorp;
    }
}
