package org.zyf.javabasic.thread.immutableobject;

/**
 * @author yanfengzhang
 * @description 彩信中心的相关数据 模式角色：ImmutableObject.ImmutableObject
 * 使用不可变对象表示彩信中心的相关数据
 * @date 2021/1/24  17:43
 */
public final class MMSCInfo {
    /**
     * 设备编号
     */
    private final String deviceID;
    /**
     * 彩信中心URL
     */
    private final String url;
    /**
     * 该彩信中心支持的最大附件大小
     */
    private final int maxAttachmentSizeInbytes;

    public MMSCInfo(String deviceID, String url, int maxAttachmentSizeInbytes) {
        this.deviceID = deviceID;
        this.url = url;
        this.maxAttachmentSizeInbytes = maxAttachmentSizeInbytes;
    }

    public MMSCInfo(MMSCInfo prototype) {
        this.deviceID = prototype.deviceID;
        this.url = prototype.url;
        this.maxAttachmentSizeInbytes = prototype.maxAttachmentSizeInbytes;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeInbytes() {
        return maxAttachmentSizeInbytes;
    }
}
