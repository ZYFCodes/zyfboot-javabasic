package org.zyf.javabasic.thread.immutableobject;

/**
 * @author yanfengzhang
 * @description 与运维中心（Operation and Maintenance Center）对接的类<BR>  模式角色：ImmutableObject.Manipulator
 * 处理彩信中心信息、路由表的变更
 * @date 2021/1/24  17:54
 */
public class OMCAgent extends Thread {
    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updatedTableName = null;
        while (true) {
            /* todo 省略其他代码*/
            /*
             * 从与OMC连接的Socket中读取消息并进行解析, 解析到数据表更新消息后,重置MMSCRouter实例。
             */
            if (isTableModificationMsg) {
                if ("MMSCInfo".equals(updatedTableName)) {
                    MMSCRouter.setInstance(new MMSCRouter());
                }
            }
            /* todo 省略其他代码*/
        }
    }
}

