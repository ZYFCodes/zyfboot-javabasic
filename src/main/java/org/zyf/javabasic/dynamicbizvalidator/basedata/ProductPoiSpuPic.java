package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:54
 */
@Data
@Builder
public class ProductPoiSpuPic {
    private long poiId;
    private long poiSpuId;
    private long id;
    private int ctime;
    private int utime;
    private String picLargeUrl;
    private String picSmallUrl;
    private int sequence;
    private int isScored;
    private double qualityScore;
    private double whiteRateScore;
    private String specialEffectUrl;
    private int specialEffectEnable;
    private int isMaster;
    private int borderResult;
    private double borderScore;
    private int blurResult;
    private double blurScore;
    private int recaptureResult;
    private double recaptureScore;
    private String picExtend;
}
