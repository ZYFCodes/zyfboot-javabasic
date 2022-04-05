package org.zyf.javabasic.designpatterns.template.thrift;

/**
 * @author yanfengzhang
 * @description 用于利用scroll形式滚动调用thrift
 * @date 2022/3/18  23:23
 */
public abstract class ThriftInvokeScrollCommand<TC, TR, FR> extends ThriftInvokeCommand<TC, TR, FR> {
    private Long scrollId = 0L;

    public ThriftInvokeScrollCommand(TC command) {
        super(command);
    }

    @Override
    public void preHandle() {
        /*command 设置 ScrollId*/
        setScrollId(command, scrollId);
    }

    @Override
    public void afterHandle(TR thriftResult) {
        /*从 thriftResult 获取 scrollId*/
        scrollId = getNextScrollId(thriftResult);
    }

    /**
     * command 设置 ScrollId
     */
    protected abstract void setScrollId(TC command, Long scrollId);

    /**
     * 从 thriftResult 获取 scrollId
     */
    protected abstract Long getNextScrollId(TR thriftResult);
}
