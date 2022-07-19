package org.zyf.javabasic.aop.complex.service.activity;

/**
 * 活动基本接口
 *
 * @author yanfengzhang
 */
public interface ActivityService<T> {
    /**
     * 查询对应活动详细信息
     *
     * @param args 被拦截方法的参数
     * @return 基于活动详细信息构造的业务返回值
     */
    T queryActivityDetail(Object[] args);

    /**
     * 创建或者更新活动详细信息
     *
     * @param args 被拦截方法的参数
     * @return 基于活动详细信息构造的业务返回值
     */
    T createOrUpdateActivityDetail(Object[] args);

    /**
     * 将活动详细信息置为删除-----软删除
     *
     * @param args 被拦截方法的参数
     */
    boolean deleteActivityDetail(Object[] args);

    /**
     * 检查活动详细信息
     *
     * @param args 被拦截方法的参数
     */
    boolean checkActivityInfo(Object[] args);

    /**
     * 活动详细信息上线
     *
     * @param args 被拦截方法的参数
     */
    boolean onlineActivity(Object[] args);

    /**
     * 活动详细信息下线
     *
     * @param args 被拦截方法的参数
     */
    boolean offlineActivity(Object[] args);
}
