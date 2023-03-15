package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:37
 */
@Data
@Builder
public class LadderBoxPrice {
    private int status;
    private int ladderNum;
    private double ladderPrice;
}
