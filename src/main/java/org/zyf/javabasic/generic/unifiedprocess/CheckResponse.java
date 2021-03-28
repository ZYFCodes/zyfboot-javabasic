package org.zyf.javabasic.generic.unifiedprocess;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 检查结果信息
 * @date 2021/2/9  23:02
 */
@Data
@Builder
public class CheckResponse {
    /**
     * 响应码
     */
    public int code;
    /**
     * 返回的数据
     */
    public Object data;
    /**
     * 时间戳
     */
    public Long timestamp;

    /**
     * 消息
     */
    public String msg;

    public static CheckResponse error() {
        return CheckResponse.builder().code(20001).data(false).timestamp(System.currentTimeMillis()).build();
    }

    public static CheckResponse error(Object data) {
        return CheckResponse.builder().code(20001).data(data).timestamp(System.currentTimeMillis()).build();
    }

    public static CheckResponse error(int code, Object data) {
        return CheckResponse.builder().code(code).data(data).timestamp(System.currentTimeMillis()).build();
    }

    public static CheckResponse success() {
        return CheckResponse.builder().code(20000).data(true).timestamp(System.currentTimeMillis()).build();
    }

    public static CheckResponse success(Object data) {
        return CheckResponse.builder().code(20000).data(data).timestamp(System.currentTimeMillis()).build();
    }

    public static CheckResponse success(int code, Object data) {
        return CheckResponse.builder().code(code).data(data).timestamp(System.currentTimeMillis()).build();
    }
}
