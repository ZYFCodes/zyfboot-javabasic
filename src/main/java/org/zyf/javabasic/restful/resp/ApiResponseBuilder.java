package org.zyf.javabasic.restful.resp;

import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 定义响应类的建造者类 ApiResponseBuilder
 * @author: zhangyanfeng
 * @create: 2023-10-30 19:17
 **/
public class ApiResponseBuilder<T> {
    private int status;
    private String message;
    private T data;
    private Map<String, Object> metadata;

    public ApiResponseBuilder<T> status(int status) {
        this.status = status;
        return this;
    }

    public ApiResponseBuilder<T> message(String message) {
        this.message = message;
        return this;
    }

    public ApiResponseBuilder<T> data(T data) {
        this.data = data;
        return this;
    }

    public ApiResponseBuilder<T> metadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    public ApiResponse<T> build() {
        return new ApiResponse<>(status, message, data, metadata);
    }
}
