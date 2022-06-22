package org.zyf.javabasic.designpatterns.template.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:14
 */
public enum ErrorCode {
    SUCCESS(0, "成功"), FAILED(1, "失败"),
    UPC_CHECK_SUCCESS(200, "成功"),
    // 未知错误
    UNKNOW_ERROR(-999, "未知服务端异常"),
    //通用异常
    PARAMS_ERROR(1000, "请求参数错误"),
    SERVER_ERROR(1001, "服务端内部异常"),
    SERVER_ERROR_EXTEND(1001, "服务端内部异常：%s"),
    WM_SERVICE_ERROR(1002, "外卖服务化系统异常"),
    REQUEST_PARAMS_ERROR(1003, "参数转换错误"),
    FILE_TOO_LARGE(1004, "您上传的文件不能超过%sM"),
    FILE_NOT_FOUND(1005, "请上传文件"),
    FILE_NAME_ILLEGALITY(1006, "文件名不规范"),
    FILE_EXT_NAME_ILLEGALITY(1007, "文件类型不正确，请上传扩展名为%s的文件"),
    FILE_TEMPLATE_ILLEGALITY(1008, "模版不正确"),
    DATA_TOO_MATCH(1009, "数据过多"),
    SENSITIVE_WORD(1010, "存在敏感词"),
    PARAMS_CHECK_FAILED(1011, "参数校验失败：%s"),
    DATA_TOO_MATCH_V2(1012, "数据过多：提交数据量%s，最大数据量%s"),
    DATA_NOT_FOUND(1013, "没有找到数据"),
    DATA_NOT_FOUND_EXTEND(1013, "没有找到数据：%s"),
    ORIGIN_NOT_FOUND(1023, "产地没有找到数据"),
    RPC_SERVICE_ERROR(1024, "服务化系统异常"),
    CATEGORY_NOT_FOUND(1025, "查询不到类目数据"),

    MERCHANT_EXIST(10020, "该账户已存在"),
    MERCHANT_NOT_EXIST(10021, "该账户不存在"),
    MERCHANT_AGGR_IN(10022, "该账户正在聚合商品"),

    AUDIT_BRAND_ID_EMPTY(10023, "审核记录ID不能为空"),
    REASON_EMPTY(10024, "拒绝原因不能为空"),
    UPDATE_FAIL(10025, "更新失败请重试"),
    OPERATION_FAIL(10026, "操作失败，请重试"),
    CURRENT_USER_RECORD_EMPTY(10027, "当前登录用户下查询不到该审核任务"),
    WM_PRODUCT_UPDATE_FAIL(10028, "外卖品牌库更新失败"),
    WM_PRODUCT_INSERT_FAIL(10029, "外卖品牌库新增失败"),
    CRUX_BRAND_SAVE_BACK_FAIL(10030, "标品品牌库回写失败"),
    WM_BRAND_NOT_EXIST(10031, "闪购存在,外卖品牌不存在"),
    CRUX_SERVICE_EXCEPTION(10032, "调用CRUX服务异常"),
    WM_BRAND_SERVICE_EXCEPTION(10033, "交易系统平台品牌服务异常"),
    BRAND_EXIST_DONT_UPDATE(10034, "该品牌已存在，请驳回当前任务"),
    MERCHANT_SERVER_ERROR(10040, "商家商品中心服务异常"),
    STANARDQUERY_SERVER_ERROR(10041, "调用standardquery服务异常"),


    BRAND_MERCHANT_ID_EMPTY(10050, "标品ID不能为空"),
    BRAND_MERCHANT_BATCH_LIMIT(10051, "批量限制不能超过10个"),
    BRAND_MERCHANT_AUDIT_STATUS_INVALID(10052, "审核状态传值错误"),
    BRAND_MERCHANT_REMOVE_RETRY(10053, "移除失败请重试"),
    EXIT_ING_SKU_CLEAN(10054, "存在分配中的任务"),


    ILLEGAL_DATA(1014, "数据不合法"),
    ES_LIMIT_10000_MSG(1015, "查询数据量太大，请增加检索条件后再试"),

    ILLEGAL_STAFF_MISID(1020, "输入人员misId不合法"),
    CRUX_SERVICE_ERROR(1030, "CRUX服务化系统异常"),
    ILLEGAL_CHECKER_MISID(1040, "审核人员misId不合法"),
    ILLEGAL_TRANSFERFROM_MISID(1050, "任务转出人员misId不合法"),
    ILLEGAL_TRANSFERTO_MISID(1060, "任务转入人员misId不合法"),
    EMPTY_DOWNREASON(1070, "标品下架原因不得为空"),
    STATUS_REPEAT_FAIL(1080, "标品状态已更新，请勿重复操作"),


    // 业务错误
    NON_LEAF_CATEGORY(10001, "该分类节点不是叶子分类节点：分类id[%s]，分类名称[%s]"),
    DUPLICATE_EAN(10002, "UPC/EAN或商品名称已存在"),
    CATEGORY_NAME_CHANGE(10003, "数据保存成功，但分类名称发生变更请知悉"),
    ERROR_RECOVERYING(10004, "存在纠错中字段，不能发布"),
    CATEGORY_IS_NOT_LEAF(10005, "标品必须挂载到后台分类的叶子节点上"),
    PIC_OVER_MAX_LENGTH(10006, "超出图片最大长度(800字符)"),
    DUPLICATE_PRODUCT(10007, "商品内容已存在"),
    DUPLICATE_CATEGORY(10008, "该人员此一级类目已分配"),
    HAS_RELATED_PRODUCT(10009, "该标品存在关联商品"),

    NOT_EQUAL_ISSP_PRODUCT(10010, "标品与非标品之间无法转移"),
    NOT_DUPLICATE_PRODUCT(10011, "关键属性不一致"),

    WAIMAI_BIND_PROPERTY_ERROR(10012, "绑定外卖属性错误"),
    CATEGORY_EMPTY_ERROR(10013, "该类目不存在"),
    UPC_ORIGIN_NOT_EXIST(10014, "条码无关联产地"),
    TEMPLATE_NOT_EXIST(10015, "模板类型不存在"),

    ZH_BRAND_DUPLICATE(10020, "品牌中文名已存在"),
    EN_BRAND_DUPLICATE(10021, "品牌英文名已存在"),
    BRAND_NOT_EXIST(10023, "品牌未找到"),
    AUDIT_BRAND_DUPLICATE(10024, "未校准品牌中文名已存在"),
    CLEAN_SKU_UNEXIST(10025, "审核标品id没有找到"),
    CLEAN_SOURCE_SKU_UNEXIST(10026, "审核标品源id没有找到"),

    DUPLICATE_EAN_EXIST(10002, "UPC/EAN已存在"),

    PARENT_ORIGIN_UNEXIST(10031, "父产地id不存在"),

    AUDITER_UNEXIST(10026, "审核人信息没有找到"),

    BRAND_STATUS_UNENABLED(10040, "当前品牌未启用，请检查授权品牌并重新选择品牌！"),
    BRAND_UNAUTHORIZED(10041, "品牌未授权，请检查授权品牌并重新选择品牌！"),
    BRAND_CATEGORY_UNAUTHORIZED(10042, "类目未授权，请重新选择类目！"),
    BRAND_NOT_THREE_LEVEL_CATEGORY(10043, "类目非线上三级类目，请查看最新三级类目表并重新选择类目！"),
    BRAND_UPC_REPEAT(10044, "UPC/EAN/ISBN请勿重复填写！"),
    BRAND_UPC_EXIST(10045, "UPC/EAN/ISBN已存在！"),
    BRAND_NAME_EXIST(10046, "商品名称已存在！请勿重复创建"),


    //权限控制
    USER_ROLE_EMPTY(20001, "用户角色为空"),
    USER_ROLE_NOT_MATCH_CATEGORY_TYPE(20002, "权限不足，请先申请权限！"),
    BRAND_MANUFACTURE_VALID_ERROR(20003, "品牌商登录信息获取失败"),
    BRAND_MANUFACTURE_AUTHORIZE_ERROR(20005, "品牌商授权类型获取失败"),
    BRAND_MANUFACTURE_NOT_AUTHORIZE(20006, "品牌或类目未授权"),
    BRAND_MANUFACTURE_AUTHORIZE_NO_CATEGORY(20007, "品牌商授权类目为空"),

    //药品相关
    MEDICINE_CON(30001, "是否关联疾病/病症不能为空"),
    CATEGORY_NODE_FOUND(10041, "没有找到类目数据"),
    CATEGORY_DISENABLE(10042, "类目状态为禁用"),
    MEDICINE_CONFLICT_DISEASES_CON(10043, "主副疾病互斥冲突"),//crux透传占位
    MEDICINE_CONFLICT_SYMPTOM_CON(10044, "主副病症互斥冲突");//crux透传占位

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static final Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        ErrorCode[] errorCodes = values();
        for (ErrorCode errorCode : errorCodes) {
            map.put(errorCode.getCode(), errorCode.getMsg());
        }
    }

}

