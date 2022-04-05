package org.zyf.javabasic.designpatterns.template.thrift;

/**
 * @author yanfengzhang
 * @description 用于利用分页形式滚动调用thrift
 * @date 2022/3/18  23:25
 */
public abstract class ThriftInvokePageCommand<TC, TR, FR> extends ThriftInvokeCommand<TC, TR, FR> {
    private int pageNo = 1;

    public ThriftInvokePageCommand(TC command) {
        super(command);
    }

    @Override
    public void preHandle() {
        /*command 设置 pageNo*/
        setPageNo(command, pageNo);
    }

    @Override
    public void afterHandle(TR thriftResult) {
        /*页数加 1*/
        pageNo++;
    }

    /**
     * command 设置 pageNo
     */
    protected abstract void setPageNo(TC command, int pageNo);
}
