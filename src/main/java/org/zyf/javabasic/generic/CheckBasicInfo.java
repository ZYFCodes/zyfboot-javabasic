package org.zyf.javabasic.generic;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 审核基本数据，相关数据存放到对应的数据库中
 * @date 2021/1/28  23:35
 */
@Data
@Builder
public class CheckBasicInfo {
    /**
     * 业务主键ID
     */
    private Integer id;
    /**
     * 审核码，使用uuid生成唯一标识
     */
    private String checkCode;
    /**
     * 业务类型，例如接入审核的业务：电影、电视剧、综艺、动画、游戏、音乐
     */
    private Integer bizType;
    /**
     * 业务id，具体对应审核的业务id主键号
     */
    private Long bizId;
    /**
     * 业务名称
     */
    private String bizName;
    /**
     * 当前业务的基本状态：上线、下线、在线修改、
     */
    private Integer bizStatus;
    /**
     * 审核申请类型，对应业务接入审核时的申请审核类型
     */
    private Integer checkType;
    /**
     * 当前审核状态：已提交、已撤销、已驳回、已暂停、已废弃、已通过
     */
    private Integer status;
    /**
     * 审核人员，直接记录具体审核人员
     */
    private String approver;
    /**
     * 当前审核提交人员
     */
    private String creator;
    /**
     * 选填，审核申请或审核操作驳回时对应的基本评论信息
     */
    private String comment;
    /**
     * 选填，具体的扩展信息补注
     */
    private String extraInfo;
    /**
     * 当前审核的具体内容信息
     */
    private String content;
    /**
     * 审核创建时间
     */
    private Integer ctime;
    /**
     * 审核更新时间
     */
    private Integer utime;
}
