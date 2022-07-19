//package org.zyf.javabasic.aop.mock;
//
//import com.alibaba.fastjson.JSON;
//import com.dianping.lion.Environment;
//import com.google.common.cache.CacheBuilder;
//import com.google.common.cache.CacheLoader;
//import com.google.common.cache.LoadingCache;
//import com.google.common.collect.Lists;
//import com.zyf.dorado.common.RpcRole;
//import com.zyf.dorado.rpc.handler.filter.Filter;
//import com.zyf.dorado.rpc.handler.filter.FilterHandler;
//import com.zyf.dorado.rpc.meta.RpcInvocation;
//import com.zyf.dorado.rpc.meta.RpcResult;
//import com.zyf.service.mobile.mtthrift.client.pool.MTThriftPoolConfig;
//import com.zyf.service.mobile.mtthrift.proxy.ThriftClientProxy;
//import com.zyf.product.bsap.thrift.domain.common.ProductBsapThirftException;
//import com.zyf.product.bsap.thrift.domain.mock.MockRuleDto;
//import com.zyf.product.bsap.thrift.iface.MockRuleThriftService;
//import com.zyf.tsp.product.bsap.enums.MockRuleType;
//import com.zyf.tsp.product.bsap.mcc.BsapCommonSwitch;
//import lombok.extern.log4j.Log4j2;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.thrift.TException;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.regex.Pattern;
//
///**
// * @author yanfengzhang
// * @description mock组件对指定接口按指定内容进行干预
// * @date 2021/12/28  23:33
// */
//@Log4j2
//@Component
//public class MockRemoteThriftClientFilter implements Filter {
//
//    private static MockRuleThriftService.Iface mockRuleThriftService = null;
//    private static final String MOCK_CLIENT_APPKEY = BsapCommonSwitch.getMockClientApp();
//    private static final String MOCK_SERVER_APPKEY = "com.zyf.product.bsap";
//    private static volatile Set<String> rpcInfos = new HashSet<>();
//
//    static {
//        MockRemoteThriftClientFilter.init(MOCK_CLIENT_APPKEY);
//    }
//
//    private static LoadingCache<String, List<MockRuleDto>> mockRuleCache = CacheBuilder.newBuilder()
//            /*初始化加载一次，后续每隔十分钟刷新缓存数据*/
//            .refreshAfterWrite(BsapCommonSwitch.getMockRuleReflushTime(), TimeUnit.MINUTES)
//            /*构建缓存*/
//            .build(new CacheLoader<String, List<MockRuleDto>>() {
//                /*初始化加载数据的缓存信息*/
//                @Override
//                public List<MockRuleDto> load(String str) throws TException, ProductBsapThirftException {
//                    Long total = 60L;
//                    List<MockRuleDto> mockRuleDtos = Lists.newArrayList();
//                    Long validMockRuleCount = mockRuleThriftService.getValidCount();
//                    if (validMockRuleCount < total) {
//                        /*总接口数较少时直接全量拉取*/
//                        return mockRuleThriftService.getAllMockRules();
//                    }
//                    /*数据量大时进行分次拉取*/
//                    for (long i = 0; i <= validMockRuleCount; ) {
//                        mockRuleDtos.addAll(mockRuleThriftService.getMockRulesByRange(i, i + total));
//                        i = i + total + 1;
//                    }
//                    return mockRuleDtos;
//                }
//            });
//
//    /**
//     * 初始化thrift客户端
//     */
//    public static void init(String appKey) {
//        MTThriftPoolConfig mtThriftPoolConfig = new MTThriftPoolConfig();
//        mtThriftPoolConfig.setMaxActive(5);
//        mtThriftPoolConfig.setMaxIdle(2);
//        mtThriftPoolConfig.setMinIdle(1);
//        mtThriftPoolConfig.setMaxWait(3000L);
//        mtThriftPoolConfig.setTestOnBorrow(false);
//
//        ThriftClientProxy mockClientProxy = new ThriftClientProxy();
//        mockClientProxy.setMtThriftPoolConfig(mtThriftPoolConfig);
//        mockClientProxy.setAppKey(appKey);
//        mockClientProxy.setRemoteAppkey(MOCK_SERVER_APPKEY);
//        mockClientProxy.setTimeout(5000);
//        mockClientProxy.setServiceInterface(MockRuleThriftService.class);
//        mockClientProxy.setFilterByServiceName(true);
//
//        try {
//            mockClientProxy.afterPropertiesSet();
//            mockRuleThriftService = (MockRuleThriftService.Iface) mockClientProxy.getObject();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static List<MockRuleDto> loadData(String str) throws TException, ProductBsapThirftException {
//        Long total = 60L;
//        List<MockRuleDto> mockRuleDtos = Lists.newArrayList();
//        Long validMockRuleCount = mockRuleThriftService.getValidCount();
//        if (validMockRuleCount < total) {
//            /*总接口数较少时直接全量拉取*/
//            return mockRuleThriftService.getAllMockRules();
//        }
//        /*数据量大时进行分次拉取*/
//        for (long i = 0; i <= validMockRuleCount; ) {
//            mockRuleDtos.addAll(mockRuleThriftService.getMockRulesByRange(i, i + total));
//            i = i + total + 1;
//        }
//        return mockRuleDtos;
//    }
//
//    /**
//     * 将此过滤进行优先处理，优先级至高
//     *
//     * @return 过滤器优先级优先级
//     */
//    @Override
//    public int getPriority() {
//        return 1000;
//    }
//
//    /**
//     * 本sdk作为本身的调用服务进行处理
//     *
//     * @return
//     */
//    @Override
//    public RpcRole getRole() {
//        return RpcRole.INVOKER;
//    }
//
//    /**
//     * 实时拦截干预处理
//     *
//     * @param rpcInvocation rpc调用消息
//     * @param filterHandler 过滤处理
//     * @return 过滤处理结果
//     * @throws Throwable 异常消息
//     */
//    @Override
//    public RpcResult filter(RpcInvocation rpcInvocation, FilterHandler filterHandler) throws Throwable {
//        final String MOCK_RULE_DATA = "mock_data";
//
//        /*1.特定service不进行干预*/
//        if (BsapCommonSwitch.getCommonRpcAppKeyList().contains(rpcInvocation.getServiceInterface().getName())) {
//            return filterHandler.handle(rpcInvocation);
//        }
//
//        /*特定服务进行记录相关内容*/
//        if (BsapCommonSwitch.getMockRpcInfo() && Environment.getAppName().equals(BsapCommonSwitch.getMockClientApp())) {
//            rpcInfos.add(rpcInvocation.getServiceInterface().getName() + ":" + rpcInvocation.getMethod().getName());
//            FileWriteUtils.writeToFile(rpcInfos.toString(), "/var/zyf/rpcInfo.txt");
//        }
//
//        /*2.无mock规则直接按原方式进行处理*/
//        List<MockRuleDto> mockRuleDtos = mockRuleCache.getUnchecked(MOCK_RULE_DATA);
//        if (CollectionUtils.isEmpty(mockRuleDtos)) {
//            /*全新修改0516：如果不在mock配置中都返回默认数据干预*/
//            return defaultDealResult(rpcInvocation);
//        }
//
//        /*3.查看当前客户端调用接口是否在mock规则的配置中*/
//        MockRuleDto mockRuleDto = mockRuleDtos.stream()
//                .filter(mockRuleDtoInfo -> mockRuleDtoInfo.getInterfaceInfo().equals(rpcInvocation.getServiceInterface().getName())
//                        && mockRuleDtoInfo.getMethodName().equals(rpcInvocation.getMethod().getName()))
//                .findAny().orElse(null);
//
//        /*4.如果对应接口和方法并不是指定的mock数据，直接按原方式进行处理（后续改成不在配置中的直接抛错）*/
//        if (null == mockRuleDto) {
//            /*全新修改0516：如果不在mock配置中都返回默认数据干预*/
//            return defaultDealResult(rpcInvocation);
//        }
//
//        /*5.查看当前的mock规则，如果是透传则直接进行处理*/
//        int isMock = mockRuleDto.getIsMock();
//        if (MockRuleType.PENETRATE.getType() == isMock) {
//            /*如果是透传，则直接按原方式进行处理*/
//            return filterHandler.handle(rpcInvocation);
//        }
//
//        /*6.如果是拒绝类型则直接抛出异常*/
//        if (MockRuleType.EWJECT.getType() == isMock) {
//            /*如果是拒绝，则直接按异常返回*/
//            throw new Exception("当前rpc接口mock规则为拒绝策略，不允许远程调用！");
//        }
//
//        /*7.需要进行mock的，按当前的处理方式进行mock数据的返回*/
//        /*返回的具体类*/
//        String resultTypeName = rpcInvocation.getMethod().getAnnotatedReturnType().getType().getTypeName();
//        if (resultTypeName.equals("void") && mockRuleDto.getResult().equals("void")) {
//            return new RpcResult(true);
//        }
//        if (mockRuleDto.getResult().equals("true") && (resultTypeName.equals("boolean") || resultTypeName.equals("java.lang.Boolean"))) {
//            return new RpcResult(true);
//        }
//        if (isNumeric(mockRuleDto.getResult()) && (resultTypeName.equals("int") || resultTypeName.equals("java.lang.Integer"))) {
//            return new RpcResult(Integer.valueOf(mockRuleDto.getResult()));
//        }
//        if (isNumeric(mockRuleDto.getResult()) && (resultTypeName.equals("long") || resultTypeName.equals("java.lang.Long"))) {
//            return new RpcResult(Long.valueOf(mockRuleDto.getResult()));
//        }
//        if (isDoubleOrFloat(mockRuleDto.getResult()) && (resultTypeName.equals("double") || resultTypeName.equals("java.lang.Double"))) {
//            return new RpcResult(Double.valueOf(mockRuleDto.getResult()));
//        }
//        if (isDoubleOrFloat(mockRuleDto.getResult()) && (resultTypeName.equals("float") || resultTypeName.equals("java.lang.Float"))) {
//            return new RpcResult(Float.valueOf(mockRuleDto.getResult()));
//        }
//
//        Object rpcResult = JSON.parseObject(mockRuleDto.getResult(), rpcInvocation.getMethod().getReturnType());
//        return new RpcResult(rpcResult);
//    }
//
//    private boolean isNumeric(String str) {
//        if (StringUtils.isBlank(str)) {
//            return false;
//        }
//
//        for (int i = str.length(); --i >= 0; ) {
//            int chr = str.charAt(i);
//            if (chr < 48 || chr > 57) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    /**
//     * 是否为浮点数？double或float类型
//     *
//     * @param str 传入的字符串
//     * @return 是浮点数返回true, 否则返回false
//     */
//    public static boolean isDoubleOrFloat(String str) {
//        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
//        return pattern.matcher(str).matches();
//    }
//
//    /**
//     * 没有mock的直接按默认的来处理
//     *
//     * @param rpcInvocation
//     * @return
//     */
//    private RpcResult defaultDealResult(RpcInvocation rpcInvocation) {
//        String resultTypeName = rpcInvocation.getMethod().getAnnotatedReturnType().getType().getTypeName();
//        if (resultTypeName.equals("void")) {
//            return new RpcResult(true);
//        }
//        if (resultTypeName.equals("boolean") || resultTypeName.equals("java.lang.Boolean")) {
//            return new RpcResult(true);
//        }
//        if (resultTypeName.equals("int") || resultTypeName.equals("java.lang.Integer")) {
//            return new RpcResult(0);
//        }
//        if (resultTypeName.equals("long") || resultTypeName.equals("java.lang.Long")) {
//            return new RpcResult(0L);
//        }
//        if (resultTypeName.equals("double") || resultTypeName.equals("java.lang.Double")) {
//            return new RpcResult(0D);
//        }
//        if (resultTypeName.equals("float") || resultTypeName.equals("java.lang.Float")) {
//            return new RpcResult(0F);
//        }
//        return null;
//    }
//
//}
//
