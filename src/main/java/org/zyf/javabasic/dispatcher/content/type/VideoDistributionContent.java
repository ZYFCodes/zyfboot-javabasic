package org.zyf.javabasic.dispatcher.content.type;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionType;

/**
 * @program: zyfboot-javabasic
 * @description: 视频分发内容
 * @author: zhangyanfeng
 * @create: 2024-04-21 15:50
 **/
@Data
public class VideoDistributionContent extends DistributionContent {

    private static final long serialVersionUID = -338164717741524366L;
    /**
     * 视频ID
     */
    private String videoId;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 视频截图
     */
    private String thumbnail;

    /**
     * 视频截图大尺寸
     */
    private String bigThumbnail;

    /**
     * 视频时长
     */
    private int duration;

    /**
     * 视频时长格式化
     */
    private String durationFormat;

    /**
     * 视频来源的logo
     */
    private String sourceLogo;

    /**
     * 视频来源
     */
    private String source;

    /**
     * 判断视频分发内容是否有效
     *
     * @return 如果视频分发内容有效返回 true，否则返回 false
     */
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(videoId) && StringUtils.isNotBlank(videoUrl);
    }

    /**
     * 获取分发类型
     *
     * @return 分发分发
     */
    public DistributionType getDistributionType() {
        return DistributionType.VIDEO;
    }

    /**
     * 判断是否为机构分发类型
     *
     * @return 如果是机构分发返回 true，否则返回 false
     */
    public boolean isMechanism() {
        return true;
    }

    /**
     * 获取机构分发 ID
     *
     * @return 机构分发 ID
     */
    public String getMechanismId() {
        return videoId;
    }

    /**
     * 返回对象的字符串表示形式
     *
     * @return 对象的字符串表示形式
     */
    @Override
    public String toString() {
        return "VideoDistributionContent{" +
                "videoId='" + videoId + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", bigThumbnail='" + bigThumbnail + '\'' +
                ", duration=" + duration +
                ", durationFormat='" + durationFormat + '\'' +
                ", sourceLogo='" + sourceLogo + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
