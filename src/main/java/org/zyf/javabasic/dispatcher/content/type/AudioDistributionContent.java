package org.zyf.javabasic.dispatcher.content.type;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionType;

/**
 * @program: zyfboot-javabasic
 * @description: 音频分发数据
 * @author: zhangyanfeng
 * @create: 2024-04-21 15:44
 **/
@Data
public class AudioDistributionContent extends DistributionContent {

    private static final long serialVersionUID = 1160604896625906534L;
    /**
     * 音频id
     */
    private String audioId;

    /**
     * 音频url
     */
    private String audioUrl;

    /**
     * 音频时长，单位：秒
     */
    private long duration;

    /**
     * 判断音频分发内容是否有效
     *
     * @return 如果音频分发内容有效返回 true，否则返回 false
     */
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(audioId) && StringUtils.isNotBlank(audioUrl);
    }

    /**
     * 获取分发类型
     *
     * @return 分发分发
     */
    public DistributionType getDistributionType() {
        return DistributionType.AUDIO;
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
        return audioId;
    }

    /**
     * 返回对象的字符串表示形式
     *
     * @return 对象的字符串表示形式
     */
    @Override
    public String toString() {
        return "AudioDistributionContent{" +
                "audioId='" + audioId + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", duration=" + duration +
                '}';
    }
}
