package org.zyf.javabasic.servicegovernance.constants;

import org.zyf.javabasic.servicegovernance.util.ProcessInfoUtil;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/21  23:55
 */
public class Consts {
    public static final String LOCALHOST = "127.0.0.1";
    public static final boolean isOnline = ProcessInfoUtil.isLocalHostOnline();

    private static final String mnsUrl = isOnline ? "http://mns.sankuai.com" : "http://mns.inf.test.sankuai.com";

    public static final String sgAgentRootPath = "/opt/meituan/apps/sg_agent";


    public static final int defaltMaxResponseMessageBytes = 10 * 1024 * 1024;
    public static final int defaultTimeoutInMills = 5000;
    public static final String sg_sentinelAppkey = "com.sankuai.inf.sg_sentinel";
    public static final String sg_sentinelAppkeyFile = sgAgentRootPath + "/sentinel.conf";
    public static final String sg_envFile = sgAgentRootPath + "/sg_agent_env.xml";
    public static final String sg_idcFile = sgAgentRootPath + "/idc.xml";
    public static final int connectTimeout = 500;
    public static final String getServerListApi = mnsUrl + "/api/servicelist?";
    public static final String getIdcInfoApi = mnsUrl + "/api/idcinfo?";
    public static final String getIdcXmlApi = mnsUrl + "/api/idcxml";
    public static final int fileConfigUpdateInterval = 2;

    /***** servicaCache const start *****/
    /** 默认每30s强制拉取全量 **/
    public static final int DEFAULT_FULL_PULLING_INTERVAL = 30 * 1000;
    /** 每5秒更新一次缓存 **/
    public static final int defaultUpdateTime = 5;
    /** ProtocolResponse 返回成功的 getErrcode() **/
    public static final int PROTOCOL_RESPONSE_SUCCESS = 0;
    /** ProtocolResponse 返回版本未改变 **/
    public static final int PROTOCOL_RESPONSE_NO_VERSION_UPDATE = 304;
    /** 找不到节点 **/
    public static final int PROTOCOL_RESPONSE_NODE_NOT_FOUND = -101;
    /** 默认版本号，拉取全量信息 **/
    public static final  int DEFAULT_VERSION = -1;
    /***** servicaCache const end *****/

    public static final String PIGEON_ENV_FILE = "/data/webapps/appenv";
    @Deprecated
    public static final String PIGEON_ENV_KEY = "deployenv";
    @Deprecated
    public static final String PIGEON_ENV_VALUE_ONLINE = "product";
    public static final String serverListEmptyWiki = "http://wiki.sankuai.com/x/_pXnFw";

    public enum env {
        prod, stage, test

    }

    public static final String comma = ",";
    public static final String colon = ":";
    public static final String vbar = "|";
    /**
     * @deprecated
     */
    @Deprecated
    public static final String offlineAgents = "10.4.241.165:5266,10.4.241.166:5266,10.4.241.125:5266,10.4.246.240:5266";

    /**
     * @deprecated
     */
    @Deprecated
    public static final String onlineAgents = "10.32.32.111:5266,10.64.35.159:5266";

    /**
     * @deprecated
     */
    @Deprecated
    public static final String localAgent = "127.0.0.1:5266";

    /**
     * @deprecated
     */
    @Deprecated
    public static final String envAgentUrl = "octo.agentHost";

    private Consts() {

    }
}

