package org.zyf.javabasic.restful.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 泛型封装的响应类 ApiResponse
 * @author: zhangyanfeng
 * @create: 2023-10-30 19:16
 **/
@ApiModel(description = "通用API响应")
public class ApiResponse<T> {
    @ApiModelProperty(value = "响应状态码")
    private int status;

    @ApiModelProperty(value = "响应消息")
    private String message;

    @ApiModelProperty(value = "响应数据")
    private T data;

    @ApiModelProperty(value = "元数据")
    private Map<String, Object> metadata;

    public ApiResponse(int status, String message, T data, Map<String, Object> metadata) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.metadata = metadata;
    }

    // Getter and setter methods

    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    public static <T> ApiResponse<T> success(T data) {
        return (ApiResponse<T>) builder().status(200).message("请求成功").data(data).build();
    }

    public static <T> ApiResponse<T> error(int status, String message, String errorCode, String errorDetails) {
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("error_code", errorCode);
        errorData.put("error_details", errorDetails);
        return (ApiResponse<T>) builder().status(status).message(message).data(errorData).build();
    }
}
