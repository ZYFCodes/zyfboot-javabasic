package org.zyf.javabasic.project.vote.attitude;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.project.vote.core.VoteCounter;
import org.zyf.javabasic.project.vote.core.VoteManager;
import org.zyf.javabasic.project.vote.hbase.core.VoteRecordsHAO;
import org.zyf.javabasic.project.vote.hbase.model.VoteRecordHO;
import org.zyf.javabasic.project.vote.model.attitud.*;
import org.zyf.javabasic.project.vote.model.vote.*;
import org.zyf.javabasic.project.vote.utils.VoteUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 表态服务具体实现
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:20
 **/
@Service
@Slf4j
public class AttitudeServiceImpl implements AttitudeService {

    @Autowired
    private VoteManager voteManager;
    @Autowired
    private VoteRecordsHAO voteRecordsHAO;
    @Autowired
    private VoteCounter voteCounter;

    private final String SHOW = "SHOW";
    private final String UNSHOW = "UNSHOW";
    private final LoadingCache<String, List<AttitudeVo>> localCache
            = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(5)
            .build(new CacheLoader<String, List<AttitudeVo>>() {
                @Override
                public List<AttitudeVo> load(String groupId) throws IOException {
                    return loadAttitudes(groupId);
                }
            });

    /**
     * 模拟配置后台处理：
     * 如果配置表态类型多的话，本处使用DB存储
     * 如果配置的表态类型基本固定的话，本次直接读取配置中心内容即可
     */
    private List<AttitudeVo> loadAttitudes(String groupId) throws IOException {
        //模拟配置后台结果
        InputStream inputStream = getClass().getResourceAsStream("/attitudes.json");
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String attitudesStr = scanner.hasNext() ? scanner.next() : "";
        List<AttitudeGroupVo> attitudeGroupVoList = JSON.parseArray(attitudesStr, AttitudeGroupVo.class);
        if (CollectionUtils.isEmpty(attitudeGroupVoList)) {
            return Lists.newArrayList();
        }
        List<AttitudeVo> attitudeVos = attitudeGroupVoList.stream().filter(
                        g -> StringUtils.equalsIgnoreCase(groupId, g.getGroupId())).findFirst().orElse(new AttitudeGroupVo())
                .getAttitudes();
        if (attitudeVos == null) {
            return Lists.newArrayList();
        }
        return attitudeVos;
    }

    @Override
    public List<AttitudeVo> queryAttitude(AttitudeQueryReq attitudeQueryReq) {
        //1、获取态度组配置
        List<AttitudeVo> attitudeConfigConfigVos = null;
        try {
            attitudeConfigConfigVos = localCache.get(attitudeQueryReq.getContentType());
        } catch (ExecutionException e) {
            log.error("buildOptions {} 本地获取态度组失败", attitudeQueryReq, e);
        }
        log.info("attitudeConfigConfigVos:{}", attitudeConfigConfigVos);
        //拷贝list防止高并发时数据被篡改
        List<AttitudeVo> attitudeVos = deepCopy(attitudeConfigConfigVos);
        String votingId = VoteUtils.buildVotingId(attitudeQueryReq.getContentType(), attitudeQueryReq.getContentId());
        List<VoteOption> counterColumns = attitudeVos.stream().map(v -> {
            VoteOption voteOption = new VoteOption();
            voteOption.setKey(v.getId());
            return voteOption;
        }).collect(Collectors.toList());
        VotingInfo votingInfo = voteManager.queryVotingInfoWithCounter(votingId, attitudeQueryReq.getUserId(), counterColumns);
        if (Objects.isNull(votingInfo)) {
            attitudeVos.forEach(
                    attitudeVo -> {
                        attitudeVo.setShowed(false);
                        attitudeVo.setCount(0);
                    }
            );
            return attitudeVos;
        }

        Map<String, Long> countMap = votingInfo.getCounters().stream().collect(
                Collectors.toMap(VoteOptionVO::getKey, VoteOptionVO::getCount, (a, b) -> a));
        if (MapUtils.isEmpty(countMap)) {
            return attitudeVos;
        }
        attitudeVos.forEach(vo -> {
                    Long count = countMap.get(vo.getId());
                    if (count == null || count < 0) {
                        count = 0L;
                        if (count != null && count < 0) {
                            log.error("出现count<0场景", attitudeQueryReq);
                        }
                    }
                    vo.setCount(count);

                    if (StringUtils.equalsIgnoreCase(vo.getId(), votingInfo.getVotingResult())) {
                        vo.setShowed(true);
                    } else {
                        vo.setShowed(false);
                    }
                }
        );
        List<AttitudeVo> resultList = Lists.newArrayList();
        List<AttitudeVo> attitudeVosHasCount = attitudeVos.stream().filter(a -> a.getCount() > 0L).collect(
                Collectors.toList());
        List<AttitudeVo> attitudeVosNoCount = attitudeVos.stream().filter(a -> a.getCount() == 0L).collect(
                Collectors.toList());
        attitudeVosHasCount.sort(Comparator.comparingLong(AttitudeVo::getCount).reversed());
        resultList.addAll(attitudeVosHasCount);
        resultList.addAll(attitudeVosNoCount);
        return resultList;
    }

    /**
     * 深拷贝list，防止并发数值异常
     *
     * @param attitudeConfigConfigVos
     * @return
     */
    private List<AttitudeVo> deepCopy(List<AttitudeVo> attitudeConfigConfigVos) {
        List<AttitudeVo> attitudeVos = Lists.newArrayList();
        attitudeConfigConfigVos.forEach(
                attitudeVo -> {
                    AttitudeVo a = new AttitudeVo();
                    BeanUtils.copyProperties(attitudeVo, a);
                    a.setShowed(false);
                    a.setCount(0);
                    attitudeVos.add(a);
                }
        );
        return attitudeVos;
    }

    @Override
    public AttitudeShowRes makeAttitude(AttitudeMakeReq attitudeMakeReq) {
        AttitudeShowRes attitudeShowRes = new AttitudeShowRes();
        AttitudeShowRes errorResult = AttitudeShowRes.checkAttitudeRequest(attitudeMakeReq);
        if (errorResult != null) {
            return errorResult;
        }
        //1、发表态度（投票）
        VotingInfoReq request = VotingInfoReq.convert2VotingRequest(attitudeMakeReq);
        //获取用户当前的是否有态度
        VotingInfo current = queryVotingInfo(request.getVotingId(), request.getBizId(),
                attitudeMakeReq.getUserId(), request.getVotingResult());
        VoteRes voteResult = new VoteRes();
        if (StringUtils.equalsIgnoreCase(SHOW, attitudeMakeReq.getInteractActionType())) {
            voteResult = showAttitude(current, request, attitudeMakeReq.getDataType());
        } else if (StringUtils.equalsIgnoreCase(UNSHOW, attitudeMakeReq.getInteractActionType())) {
            voteResult = unShowAttitude(current, request, attitudeMakeReq.getDataType());
        }

        attitudeShowRes.setSuccess(voteResult.isSuccess());
        attitudeShowRes.setDesc(voteResult.getResultDesc());
        return attitudeShowRes;
    }

    private VotingInfo queryVotingInfo(String votingId, String dataId, String userId, String votingResult) {
        VotingInfo votingInfo = buildVotingInfo(votingId, dataId, userId);
        VoteRecordHO voteRecordHO = voteRecordsHAO.queryVotingRecord(votingId, userId);
        if (voteRecordHO != null && StringUtils.equalsIgnoreCase(voteRecordHO.getVote(), votingResult)) {
            votingInfo.setHasVoted(true);
            votingInfo.setVotingResult(voteRecordHO.getVote());
            votingInfo.setVotingDate(voteRecordHO.getGmtCreate());
            votingInfo.setUserId(voteRecordHO.getUserId());
        }
        return votingInfo;
    }

    private VotingInfo buildVotingInfo(String votingId, String dataId, String userId) {
        VotingInfo votingInfo = voteManager.queryVotingInfo(votingId,
                userId);
        if (votingInfo == null) {
            //创建投票器
            VoteCreateReq votingCreateRequest = new VoteCreateReq();
            votingCreateRequest.setBizId(dataId);
            votingCreateRequest.setVotingId(votingId);
            VoteRes createResult = voteManager.createVoting(votingCreateRequest);
            if (createResult.isSuccess()) {
                votingInfo = voteManager.queryVotingInfo(votingId, userId);
            }
        }
        return votingInfo;
    }

    private VoteRes showAttitude(VotingInfo current, VotingInfoReq request, String attitudeGroupId) {
        VoteRes voteResult = new VoteRes();
        //透过票并且是投的其他的
        if (current != null && current.isHasVoted() && StringUtils.equalsIgnoreCase(current.getVotingResult(),
                request.getVotingResult())) {
            //投过当前票
            voteResult.setSuccess(true);
            voteResult.setResultDesc("已经发表过该态度");
        } else if (current != null && current.isHasVoted()) {
            VotingInfoReq unVoteRequest = new VotingInfoReq();
            BeanUtils.copyProperties(request, unVoteRequest);
            unVoteRequest.setVotingId(current.getVotingId());
            unVoteRequest.setVotingResult(current.getVotingResult());
            unVote(unVoteRequest, attitudeGroupId);
            voteResult = vote(request, attitudeGroupId);
        } else {
            //其它的直接投票
            voteResult = vote(request, attitudeGroupId);
        }
        return voteResult;
    }

    private VoteRes unShowAttitude(VotingInfo current, VotingInfoReq request, String attitudeGroupId) {
        VoteRes voteResult = new VoteRes();
        if (current == null) {
            voteResult.setSuccess(true);
            voteResult.setResultDesc("已经取消态度");
            return voteResult;
        }
        //取消一样的态度才能取消
        if (StringUtils.equalsIgnoreCase(current.getVotingResult(), request.getVotingResult())) {
            voteResult = unVote(request, attitudeGroupId);
        } else {
            voteResult.setSuccess(true);
            voteResult.setResultDesc("已经取消态度");
        }
        return voteResult;
    }

    public VoteRes unVote(VotingInfoReq request, String attitudeGroupId) {
        VoteRes checkResult = checkVoteParam(request, attitudeGroupId);
        if (checkResult != null) {
            return checkResult;
        }
        //投票计数
        VoteRes voteRes = voteCounter.decr(request.getVotingId(),
                request);
        if (voteRes == null || !voteRes.isSuccess()) {
            voteRes.setSuccess(false);
            voteRes.setResultDesc("counter decrease false");
            return voteRes;
        }
        //unvote的设置为空
        request.setVotingResult("");
        return saveVoting(request);
    }

    public VoteRes vote(VotingInfoReq request, String attitudeGroupId) {
        VoteRes checkResult = checkVoteParam(request, attitudeGroupId);
        if (checkResult != null) {
            return checkResult;
        }

        //投票计数
        VoteRes voteRes = voteCounter.inc(request.getVotingId(),
                request);
        if (voteRes == null || !voteRes.isSuccess()) {
            checkResult.setSuccess(false);
            checkResult.setResultDesc("counter increment false");
            return checkResult;
        }
        return saveVoting(request);

    }

    private VoteRes saveVoting(VotingInfoReq request) {
        VoteRes result = new VoteRes();

        if (StringUtils.isNotEmpty(request.getBizId())) {
            //打印投票日志
            log.info("[attitude voting record]{},{},{}", request.getUserId(), request.getBizId(), 1);
        }
        Date votingDate = request.getVotingDate();
        //投票详情记录
        VotingStoreReq storeRequest = new VotingStoreReq();
        storeRequest.setUserId(request.getUserId());
        storeRequest.setVotingDate(votingDate);
        storeRequest.setVotingId(request.getVotingId());
        storeRequest.setVotingResult(request.getVotingResult());
        //记录topicid
        storeRequest.setBizId(request.getBizId());
        storeRequest.setTenantId(request.getTenantId());
        storeRequest.setSceneCode(request.getSceneCode());
        VoteRes storeResult = voteManager.storeVotingResult(storeRequest);
        if (storeResult == null || !storeResult.isSuccess()) {
            log.error("voting store records error,votingId={},userId={},votingResult={}",
                    request.getVotingId(), request.getUserId(), request.getVotingResult());
            result.setSuccess(false);
            result.setResultDesc("vote error");
            return result;
        }

        result.setSuccess(true);
        result.setResultDesc("vote/unvote success");
        return result;
    }

    private VoteRes checkVoteParam(VotingInfoReq request, String attitudeGroupId) {
        VoteRes result = new VoteRes();
        //获取options
        List<VoteOption> options = buildOptions(attitudeGroupId);
        if (CollectionUtils.isEmpty(options)) {
            result.setSuccess(false);
            result.setResultDesc("options is illegal");
            return result;
        }
        if (!voteOptionIsLegal(options, request.getVotingResult())) {
            result.setSuccess(false);
            result.setResultDesc("option is not exit");
            return result;
        }
        //查询投票器
        VotingInfo votingInfo = queryVotingInfo(request.getVotingId(), request.getBizId(),
                request.getUserId(), request.getVotingResult());
        if (votingInfo == null) {
            result.setSuccess(false);
            result.setResultDesc("voting is not exit");
            return result;
        }
        //投票开关：可进行修改为可配置
        result.setOpen(true);
        return null;
    }

    private List<VoteOption> buildOptions(String attitudeGroupId) {
        List<AttitudeVo> attitudeVos = null;
        try {
            attitudeVos = localCache.get(attitudeGroupId);
        } catch (ExecutionException e) {
            log.error("buildOptions {} 本地获取态度组失败", attitudeGroupId);
        }
        if (CollectionUtils.isEmpty(attitudeVos)) {
            return Lists.newArrayList();
        }
        return attitudeVos.stream().map(v -> {
            VoteOption voteOption = new VoteOption();
            voteOption.setKey(v.getId());
            return voteOption;
        }).collect(Collectors.toList());
    }

    private boolean voteOptionIsLegal(List<VoteOption> options, String option) {
        for (VoteOption voteOption : options) {
            if (StringUtils.equals(voteOption.getKey(), option)) {
                return true;
            }
        }
        return false;
    }
}

