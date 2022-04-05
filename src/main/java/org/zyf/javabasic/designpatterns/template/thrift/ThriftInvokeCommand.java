package org.zyf.javabasic.designpatterns.template.thrift;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 业务thrift调用基本类
 * * TC : thrift请求
 * * TR : thrift返回
 * * FR : 业务实际真正要的处理结果
 * @date 2022/3/18  23:18
 */
@Data
@Slf4j
public abstract class ThriftInvokeCommand<TC, TR, FR> {
    /**
     * thrift请求
     */
    protected TC command;
    /**
     * 每个命令最多翻滚多少页
     */
    private int maxPageCount = ConfigUtils.getThriftScrollMaxPageLimit();
    /**
     * 每个命令的最大重试个数
     */
    private int maxRetryCount = ConfigUtils.getThriftMaxInvokeRetryCount();
    /**
     * 每个命令的休眠时间
     */
    private int sleepMs = ConfigUtils.getThriftScrollRate();
    /**
     * 每个命令的查询页数
     */
    private int pageSize = ConfigUtils.getThriftScrollPageSize();

    public ThriftInvokeCommand(TC command) {
        this.command = command;
    }

    /**
     * 核心处理逻辑：获取指定结果
     */
    public List<FR> getResult() {
        /*执行的页数*/
        int currPageNo = 0;
        /*command 设置 pageSize*/
        setPageSize(command, pageSize);
        /*调用接口的失败次数*/
        int errorCount = 0;

        TR thriftResult = null;
        List<FR> rList = Lists.newArrayList();
        while (true) {
            try {
                /*1.前置处理*/
                preHandle();
                /*2.业务转发调用*/
                thriftResult = invoke(command);
                /*3.从thriftResult提取结果并转换为实际需要的*/
                rList.addAll(convertThriftResult(thriftResult));
                /*4.后置处理*/
                afterHandle(thriftResult);
            } catch (Exception e) {
                log.error("", e);
                errorCount++;
            }
            /*thrift调用无结果则直接停止循转*/
            if (Objects.isNull(thriftResult) || 0 == getResultSize(thriftResult)) {
                break;
            }
            if (errorCount >= maxRetryCount || currPageNo >= maxPageCount) {
                String msg = errorCount >= maxRetryCount ? "重试次数过多,已达最大尝试次数：" + maxRetryCount : "循环次数过多,已达最大尝试次数：" + maxPageCount;
                log.warn("ThriftInvokeCommand 处理警告：{}", msg);
                break;
            }
            currPageNo++;
            /*5.停顿，降低调用频率*/
            sleepInvoke();
        }
        return rList;
    }

    /**
     * thrift调用前置处理
     */
    protected abstract void preHandle();

    /**
     * thrift调用后置处理
     *
     * @param thriftResult thrift调用结果
     */
    protected abstract void afterHandle(TR thriftResult);

    /**
     * 核心逻辑， 通过命令获取结果
     *
     * @param command thrift调用请求
     * @return thrift调用结果
     */
    protected abstract TR invoke(TC command) throws Exception;

    /**
     * command 设置 pageSize
     */
    protected abstract void setPageSize(TC command, int pageSize);


    /**
     * 从 thriftResult 获取 结果长度，若是 0 ，那么 break 退出
     */
    protected abstract int getResultSize(TR thriftResult);

    /**
     * 从 thriftResult 提取结果
     */
    protected abstract List<FR> convertThriftResult(TR thriftResult);

    /**
     * 休眠调整处理
     */
    private void sleepInvoke() {
        if (sleepMs <= 0) {
            return;
        }
        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException ignored) {
        }

    }

    public ThriftInvokeCommand setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ThriftInvokeCommand setSleepMs(int sleepMs) {
        this.sleepMs = sleepMs;
        return this;
    }
}
