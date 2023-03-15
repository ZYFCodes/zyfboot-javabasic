package org.zyf.javabasic.dynamicbizvalidator.basedata;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/9  23:45
 */
@Data
@Builder
public class ProductPoiVideo {
    private long poiId;
    private long id;
    private int ctime;
    private int utime;
    private String title;
    private int length;
    private int size;
    private String urlOgg;
    private String urlMp4;
    private String mainPicLargeUrl;
    private String mainPicSmallUrl;
    private int resolutionX;
    private int resolutionY;
    private int status;
    private String coverUrl;
    private String reserved1;
}
