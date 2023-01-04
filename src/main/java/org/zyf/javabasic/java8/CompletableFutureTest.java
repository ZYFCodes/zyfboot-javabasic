package org.zyf.javabasic.java8;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.PipelineRouteConfig;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.SensitivePipelineExecutor;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.constants.SensitiveCons;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.BizType;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentAttr;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentInfoContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveHitContext;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description CompletableFuture使用
 * @date 2022/12/29  23:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CompletableFutureTest {

    @Autowired
    private SensitivePipelineExecutor sensitivePipelineExecutor;

    /**
     * 异步任务创建：有返回值的异步任务supplyAsync
     * 使用默认线程池 + 自定义线程池
     */
    @Test
    public void testSupplyAsync() throws ExecutionException, InterruptedException {
        log.info("异步任务创建-有返回值的异步任务supplyAsync:使用默认线程池（ForkJoinPool.commonPool()）");
        CompletableFuture<String> contentCleanTaskByDefault = CompletableFuture.supplyAsync(() -> {
            log.info("异步任务获取用户文本清洗结果：用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】");
            ContentCleanResContext contentCleanResContext = sensitivePipelineExecutor.getContentCleanRes(
                    ContentInfoContext.builder()
                            .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .contentAttr(ContentAttr.builder().build()).build());

            return contentCleanResContext.getCleanContent();
        });
        log.info("异步任务获取用户文本清洗结果：【{}】", contentCleanTaskByDefault.get());

        log.info("异步任务创建-有返回值的异步任务supplyAsync:自定义线程池");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<String> contentCleanTaskByDefine = CompletableFuture.supplyAsync(() -> {
            log.info("异步任务获取用户文本清洗结果：用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】");
            ContentCleanResContext contentCleanResContext = sensitivePipelineExecutor.getContentCleanRes(
                    ContentInfoContext.builder()
                            .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .contentAttr(ContentAttr.builder().build()).build());

            return contentCleanResContext.getCleanContent();
        }, threadPoolExecutor);
        log.info("异步任务获取用户文本清洗结果：【{}】", contentCleanTaskByDefine.get());
    }

    /**
     * 异步任务创建：没有返回值的异步任务runAsync
     * 使用默认线程池 + 自定义线程池
     */
    @Test
    public void testRunAsync() throws ExecutionException, InterruptedException {
        log.info("异步任务创建-有返回值的异步任务runAsync:使用默认线程池（ForkJoinPool.commonPool()）");
        CompletableFuture<Void> contentCleanTaskByDefault = CompletableFuture.runAsync(() -> {
            log.info("异步任务对用户文本清洗并刷新数据");
            log.info("异步通知进行消息转发，触达各消费端进行后续清理");
        });

        log.info("异步任务创建-有返回值的异步任务runAsync:自定义线程池");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<Void> contentCleanTaskByDefine = CompletableFuture.runAsync(() -> {
            log.info("异步任务对用户文本清洗并刷新数据");
            log.info("异步通知进行消息转发，触达各消费端进行后续清理");
        }, threadPoolExecutor);
    }

    /**
     * 异步回调：thenApply和thenApplyAsync
     * thenApply接收一个函数作为参数，使用该函数处理上一个CompletableFuture调用的结果，并返回一个具有处理结果的Future对象。
     * <p>
     * 使用thenApply方法时子任务与父任务使用的是同一个线程
     * thenApplyAsync在子任务中是另起一个线程执行任务,可以自定义线程池
     */
    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));
        CompletableFuture<SensitveHitContext> sensitveHitRes1 = contentCleanRes.thenApply((contentCleanResInfo) ->
                sensitivePipelineExecutor.getSensitveHitRes(contentCleanResInfo));
        log.info("异步回调-thenApply使用，任务一将用户文本进行清洗，任务二回调清洗结果查看数据数据命中敏感词情况");
        log.info("任务一：将用户文本进行清洗，清洗结果展示【{}】", contentCleanRes.get().getCleanContent());
        log.info("任务二：回调清洗结果查看数据数据命中敏感词情况，命中结果展示【{}】",
                sensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<SensitveHitContext> sensitveHitRes2 = CompletableFuture.supplyAsync(() ->
                        sensitivePipelineExecutor.getContentCleanRes(
                                ContentInfoContext.builder()
                                        .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                        .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                        .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()), threadPoolExecutor)
                .thenApplyAsync((contentCleanResInfo) ->
                        sensitivePipelineExecutor.getSensitveHitRes(contentCleanResInfo), threadPoolExecutor);
        log.info("异步回调-thenApplyAsync使用，任务一将用户文本进行清洗，任务二回调清洗结果查看数据数据命中敏感词情况");
        log.info("任务一：将用户文本进行清洗，清洗结果展示【{}】", contentCleanRes.get().getCleanContent());
        log.info("任务二：回调清洗结果查看数据数据命中敏感词情况，命中结果展示【{}】",
                sensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
    }

    /**
     * 异步回调：thenCompose和thenComposeAsync
     * thenCompose的参数为一个返回CompletableFuture实例的函数，该函数的参数是先前计算步骤的结果。
     * <p>
     * thenApply转换的是泛型中的类型，返回的是同一个CompletableFuture
     * thenCompose将内部的CompletableFuture调用展开来并使用上一个CompletableFutre调用的结果在下一步的CompletableFuture调用中进行运算，是生成一个新的CompletableFuture。
     */
    @Test
    public void testThenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<SensitveHitContext> sensitveHitRes1 = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build())).thenCompose(new Function<ContentCleanResContext, CompletionStage<SensitveHitContext>>() {
            @Override
            public CompletionStage<SensitveHitContext> apply(ContentCleanResContext contentCleanResInfo) {
                return CompletableFuture.supplyAsync(new Supplier<SensitveHitContext>() {
                    @Override
                    public SensitveHitContext get() {
                        return sensitivePipelineExecutor.getSensitveHitRes(contentCleanResInfo);
                    }
                });
            }
        });
        log.info("异步回调-thenCompose使用，任务一将用户文本进行清洗，任务二回调清洗结果查看数据数据命中敏感词情况");
        log.info("【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精】命中敏感词情况结果展示【{}】",
                sensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));

        CompletableFuture<SensitveHitContext> sensitveHitRes2 = CompletableFuture.supplyAsync(() ->
                        sensitivePipelineExecutor.getContentCleanRes(
                                ContentInfoContext.builder()
                                        .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                        .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                        .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()))
                .thenComposeAsync(new Function<ContentCleanResContext, CompletionStage<SensitveHitContext>>() {
                    @Override
                    public CompletionStage<SensitveHitContext> apply(ContentCleanResContext contentCleanResInfo) {
                        return CompletableFuture.supplyAsync(new Supplier<SensitveHitContext>() {
                            @Override
                            public SensitveHitContext get() {
                                return sensitivePipelineExecutor.getSensitveHitRes(contentCleanResInfo);
                            }
                        });
                    }
                });
        log.info("异步回调-thenComposeAsync使用，任务一将用户文本进行清洗，任务二回调清洗结果查看数据数据命中敏感词情况");
        log.info("【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精】命中敏感词情况结果展示【{}】",
                sensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
    }

    /**
     * 异步回调：thenAccept和thenAcceptAsync
     * 函数式接口Consumer，这个接口只有输入，没有返回值。
     * <p>
     * thenAccep方法时子任务与父任务使用的是同一个线程
     * henAccepAsync在子任务中可能是另起一个线程执行任务
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testThenAccept() throws ExecutionException, InterruptedException {
        log.info("异步回调-thenAccept使用，任务一将用户文本进行清洗，任务二回调清洗结果查看数据数据命中敏感词情况");
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));
        log.info("任务一：将用户文本进行清洗，清洗结果展示【{}】", contentCleanRes.get().getCleanContent());

        CompletableFuture<Void> sensitveHitRes = contentCleanRes.thenAccept((contentCleanResInfo) -> {
            SensitveHitContext sensitveHitContext = sensitivePipelineExecutor.getSensitveHitRes(contentCleanResInfo);
            log.info("任务二：回调清洗结果查看数据数据命中敏感词情况，命中结果展示【{}】",
                    sensitveHitContext.getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        });

        log.info("异步回调-thenAcceptAsync使用，任务一将用户文本进行清洗，任务二回调清洗结果查看数据数据命中敏感词情况");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<Void> sensitveHitRes2 = CompletableFuture.supplyAsync(() -> {
                            ContentCleanResContext contentCleanResContext = sensitivePipelineExecutor.getContentCleanRes(
                                    ContentInfoContext.builder()
                                            .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                            .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                            .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build());
                            log.info("任务一：将用户文本进行清洗，清洗结果展示【{}】", contentCleanResContext.getCleanContent());
                            return contentCleanResContext;

                        }
                        , threadPoolExecutor)
                .thenAcceptAsync((contentCleanResInfo) -> {
                    SensitveHitContext sensitveHitContext = sensitivePipelineExecutor.getSensitveHitRes(contentCleanResInfo);
                    log.info("任务二：回调清洗结果查看数据数据命中敏感词情况，命中结果展示【{}】",
                            sensitveHitContext.getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
                }, threadPoolExecutor);
    }

    /**
     * 异步回调：thenRun和thenRunAsync
     * thenRun表示某个任务执行完成后执行的动作，即回调方法，无入参，无返回值。
     * thenRun会在上一阶段 CompletableFuture计算完成的时候执行一个Runnable，而Runnable并不使用该CompletableFuture计算的结果。
     * <p>
     * thenRun方法时子任务与父任务使用的是同一个线程
     * thenRunAsync在子任务中可能是另起一个线程执行任务
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testThenRun() throws ExecutionException, InterruptedException {
        log.info("异步回调-thenRun使用，任务一将用户文本进行清洗，任务二进行异步通知清洗完成");
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));
        log.info("任务一：将用户文本进行清洗，清洗结果展示【{}】", contentCleanRes.get().getCleanContent());
        CompletableFuture<Void> notifyRes1 = contentCleanRes.thenRun(() -> {
            log.info("任务二：通知相关消费者告知已清洗完毕，可执行后续操作！");
        });

        log.info("异步回调-thenRunAsync使用，任务一将用户文本进行清洗，任务二进行异步通知清洗完成");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<Void> notifyRes2 = CompletableFuture.supplyAsync(() -> {
                            ContentCleanResContext contentCleanResContext = sensitivePipelineExecutor.getContentCleanRes(
                                    ContentInfoContext.builder()
                                            .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                            .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                            .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build());
                            log.info("任务一：将用户文本进行清洗，清洗结果展示【{}】", contentCleanResContext.getCleanContent());
                            return contentCleanResContext;

                        }
                        , threadPoolExecutor)
                .thenRunAsync(() -> {
                    log.info("任务二：通知相关消费者告知已清洗完毕，可执行后续操作！");
                }, threadPoolExecutor);
    }

    /**
     * 多任务组合：thenCombine
     * 合并两个线程任务的结果，并进一步处理，该方法有返回值。
     */
    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                regularSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<String> result = complianceSensitveHitRes.thenCombineAsync(regularSensitveHitRes, (res1, res2) -> {
            if (CollectionUtils.isEmpty(res1.getHitWords()) || CollectionUtils.isEmpty(res2.getHitWords())) {
                /*只要有一个词库反馈不命中就认为商品可以售卖*/
                return "当前商品可以售卖";
            }
            List<SensitiveWord> hitWords = Lists.newArrayList();
            hitWords.addAll(res1.getHitWords());
            hitWords.addAll(res2.getHitWords());
            return "当前商品不可以售卖，商品信息中包含敏感词" + hitWords;
        });
        log.info("组合任务一和二结论：{}", result.get());
    }

    /**
     * 多任务组合：thenAcceptBoth
     * 无返回值
     * 当两个CompletionStage都正常完成计算的时候，就会执行提供的action消费两个异步的结果
     */
    @Test
    public void testThenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                regularSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Void> result = complianceSensitveHitRes.thenAcceptBoth(regularSensitveHitRes, (res1, res2) -> {
            if (CollectionUtils.isEmpty(res1.getHitWords()) || CollectionUtils.isEmpty(res2.getHitWords())) {
                /*只要有一个词库反馈不命中就认为商品可以售卖*/
                log.info("组合任务一和二结论异步处理商品上架");
                return;
            }
            List<SensitiveWord> hitWords = Lists.newArrayList();
            hitWords.addAll(res1.getHitWords());
            hitWords.addAll(res2.getHitWords());
            log.info("组合任务一和二结论异步处理商品下架，并告知下架理由为存在敏感词【{}】", hitWords);
        });
    }

    /**
     * 多任务组合：runAfterBoth
     * 没有入参，也没有返回值
     * 两个线程任务相比较，有任何一个执行完成，就进行下一步操作，不关心运行结果。
     */
    @Test
    public void testRunAfterBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】，结果暂存redis",
                complianceSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】，结果暂存redis",
                regularSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Void> result = complianceSensitveHitRes.runAfterBoth(regularSensitveHitRes, () -> {
            log.info("组合任务一和二从缓存中获取暂存数据，以下为模拟");
            if (CollectionUtils.isEmpty(SensitveHitContext.builder().build().getHitWords()) ||
                    CollectionUtils.isEmpty(SensitveHitContext.builder().build().getHitWords())) {
                /*只要有一个词库反馈不命中就认为商品可以售卖*/
                log.info("组合任务一和二结论异步处理商品上架");
                return;
            }
            List<SensitiveWord> hitWords = Lists.newArrayList();
            hitWords.addAll(SensitveHitContext.builder().build().getHitWords());
            hitWords.addAll(SensitveHitContext.builder().build().getHitWords());
            log.info("组合任务一和二结论异步处理商品下架，并告知下架理由为存在敏感词【{}】", hitWords);
        });
    }

    /**
     * 多任务组合：applyToEither
     * 该方法有返回值
     * 两个线程任务相比较，先获得执行结果的，就对该结果进行下一步的转化操作。
     */
    @Test
    public void testApplyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}",
                regularSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<String> result = complianceSensitveHitRes.applyToEither(regularSensitveHitRes, res -> {
            if (CollectionUtils.isEmpty(res.getHitWords())) {
                /*只要有一个词库反馈不命中就认为商品可以售卖*/
                return "当前商品可以售卖";
            }
            return "当前商品不可以售卖，商品信息中包含敏感词" + res.getHitWords();
        });
        log.info("组合任务一和二处理结论：{}", result.get());
    }

    /**
     * 多任务组合：acceptEither
     * 将已经完成任务的执行结果作为方法入参，但是无返回值
     * 两个线程任务相比较，先获得执行结果的，就对该结果进行下一步的消费操作。
     */
    @Test
    public void testAcceptEither() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}",
                regularSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Void> result = complianceSensitveHitRes.acceptEither(regularSensitveHitRes, (res) -> {
            if (CollectionUtils.isEmpty(res.getHitWords())) {
                /*只要有一个词库反馈不命中就认为商品可以售卖*/
                log.info("组合任务一和二结论异步处理商品上架");
                return;
            }
            log.info("组合任务一和二结论异步处理商品下架，并告知下架理由为存在敏感词【{}】", res.getHitWords());
        });
    }

    /**
     * 多任务组合：acceptEither
     * 没有入参，也没有返回值
     * 两个线程任务相比较，有任何一个执行完成，就进行下一步操作，不关心运行结果。
     */
    @Test
    public void testRunAfterEither() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】，将结果存入redis中",
                complianceSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】，将结果存入redis中",
                regularSensitveHitRes.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Void> future = complianceSensitveHitRes.runAfterEither(regularSensitveHitRes, () -> {
            log.info("组合任务一和二从缓存中获取暂存数据，以下为模拟：直接按指定内容获取当前缓存中的结果");
            if (CollectionUtils.isEmpty(SensitveHitContext.builder().build().getHitWords())) {
                /*只要有一个词库反馈不命中就认为商品可以售卖*/
                log.info("组合任务一和二结论，从缓存中获取结果不存在敏感词直接异步处理商品上架");
                return;
            }
            log.info("组合任务一和二结论异步处理商品下架，并告知下架理由为存在敏感词【{}】", "张彦峰");
        });
    }

    /**
     * 多任务组合：allOf
     * <p>
     * 实现多 CompletableFuture 的同时返回
     * CompletableFuture是多个任务都执行完成后才会执行，只有有一个任务执行异常，则返回的CompletableFuture执行get方法时会抛出异常，如果都是正常执行，则get返回null。
     */
    @Test
    public void testAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        log.info("情况一：当给定的多个异步任务都正常完成后，返回一个新的 CompletableFuture，" +
                "给定 CompletableFuture 的结果不会反映在返回的 CompletableFuture 中，但可以通过单独检查给定任务来获得结果");
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes1 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes1 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                regularSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> thesaurusSensitveHitRes1 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.THESAURUS).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务三：根据相关业务配置进行相关词库校验匹配查看数据数据命中敏感词情况，命中结果展示【{}】",
                thesaurusSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Void> result1 = CompletableFuture.allOf(complianceSensitveHitRes1, regularSensitveHitRes1, thesaurusSensitveHitRes1);
        log.info("多任务组合：allOf执行完毕，相关词库敏感词命中情况展示：企业合规管控词库【{}】，正则敏感词库【{}】，业务自身词库【{}】",
                complianceSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                regularSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                thesaurusSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));

        log.info("情况二：当任何一个异步任务异常完成，则返回的CompletableFuture 也会异常完成，并且将该异步任务的异常作为其原因。");
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes2 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes2 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("正则词库管控处理查看数据数据命中敏感词情况异常暂停");
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                regularSensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> thesaurusSensitveHitRes2 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.THESAURUS).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务三：根据相关业务配置进行相关词库校验匹配查看数据数据命中敏感词情况，命中结果展示【{}】",
                thesaurusSensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Void> result2 = CompletableFuture.allOf(complianceSensitveHitRes2, regularSensitveHitRes2, thesaurusSensitveHitRes2);
        log.info("多任务组合：allOf执行完毕，相关词库敏感词命中情况展示：企业合规管控词库【{}】，正则敏感词库【{}】，业务自身词库【{}】",
                complianceSensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                regularSensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                thesaurusSensitveHitRes2.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));

        log.info("情况二：当存在多个异常完成时，则返回排在前面的异步任务的异常信息。");
        CompletableFuture<SensitveHitContext> complianceSensitveHitRes3 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes3.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes3 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("正则合规管控处理查看数据数据命中敏感词情况异常暂停");
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                regularSensitveHitRes3.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> thesaurusSensitveHitRes3 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("业务自身词库处理查看数据数据命中敏感词情况异常暂停");
        }, threadPoolExecutor);
        log.info("任务三：根据相关业务配置进行相关词库校验匹配查看数据数据命中敏感词情况，命中结果展示【{}】",
                thesaurusSensitveHitRes3.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Void> result3 = CompletableFuture.allOf(complianceSensitveHitRes3, regularSensitveHitRes3, thesaurusSensitveHitRes3);
        log.info("多任务组合：allOf执行完毕，相关词库敏感词命中情况展示：企业合规管控词库【{}】，正则敏感词库【{}】，业务自身词库【{}】",
                complianceSensitveHitRes3.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                regularSensitveHitRes3.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                thesaurusSensitveHitRes3.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
    }

    /**
     * 多任务组合：anyOf
     * <p>
     * 多个给定的 CompletableFuture，当其中的任何一个完成时，方法返回这个 CompletableFuture
     * CompletableFuture是多个任务只要有一个任务执行完成，则返回的CompletableFuture执行get方法时会抛出异常，如果都是正常执行，则get返回执行完成任务的结果。
     */
    @Test
    public void testAnyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<ContentCleanResContext> contentCleanRes = CompletableFuture.supplyAsync(() ->
                sensitivePipelineExecutor.getContentCleanRes(
                        ContentInfoContext.builder()
                                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰,腾讯,南京酒精")
                                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });

        CompletableFuture<SensitveHitContext> complianceSensitveHitRes1 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.COMPLIANCE).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务一：企业合规管控处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                complianceSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> regularSensitveHitRes1 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.REGULAR).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务二：正则校验处理查看数据数据命中敏感词情况，命中结果展示【{}】",
                regularSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<SensitveHitContext> thesaurusSensitveHitRes1 = CompletableFuture.supplyAsync(() -> {
            try {
                return (SensitveHitContext) PipelineRouteConfig.getInstance(SensitiveCons.Validate.THESAURUS).handle(contentCleanRes.get());
            } catch (Exception e) {
                return SensitveHitContext.builder().hitWords(Lists.newArrayList()).build();
            }
        }, threadPoolExecutor);
        log.info("任务三：根据相关业务配置进行相关词库校验匹配查看数据数据命中敏感词情况，命中结果展示【{}】",
                thesaurusSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
        CompletableFuture<Object> result1 = CompletableFuture.anyOf(complianceSensitveHitRes1, regularSensitveHitRes1, thesaurusSensitveHitRes1);
        log.info("多任务组合：allOf执行完毕，相关词库敏感词命中情况展示：企业合规管控词库【{}】，正则敏感词库【{}】，业务自身词库【{}】，allOf结果【{}】",
                complianceSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                regularSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                thesaurusSensitveHitRes1.get().getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()),
                ((SensitveHitContext) result1.get()).getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList()));
    }

    /**
     * 结果处理：whenComplete
     * <p>
     * 一般与exceptionally()配合使用，获取前一个异步线程的结果和异常
     * 不论上一个阶段是正常/异常完成(即不会对阶段的原来结果产生影响)；类似于 try..catch..finanlly 中 finally 代码块，无论是否发生异常都将会执行的。
     * 当某个任务执行完成后，会将执行结果或者执行期间抛出的异常传递给回调方法：
     * 如果是正常执行则异常为null，回调方法对应的CompletableFuture的result和该任务一致，
     */
    @Test
    public void testWhenComplete() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("异步任务获取用户文本清洗结果：用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】");
            ContentCleanResContext contentCleanResContext = sensitivePipelineExecutor.getContentCleanRes(
                    ContentInfoContext.builder()
                            .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .contentAttr(ContentAttr.builder().build()).build());
            return contentCleanResContext.getCleanContent();
        }, threadPoolExecutor).whenCompleteAsync((res, excption) -> {
            log.info("异步任务成功执行,结果是：{},异常是：", res, excption);
        }, threadPoolExecutor);
        log.info("异步任务获取用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】清洗结果:【{}】", future.get());
    }

    /**
     * 结果处理：exceptionally
     * <p>
     * 一般与whenComplete()配合使用，异常捕获范围包含前面的所有异步线程
     * 异常的结果处理（上一个阶段异常完成才会被执行）；
     * 使用方式类似于 try catch中的catch代码块中异常处理；
     * 当某个任务执行异常时将抛出的异常作为参数传递到回调方法中，如果该任务正常执行，exceptionally方法返回的CompletionStage的result就是该任务正常执行的结果。
     */
    @Test
    public void testExceptionally() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("异步任务获取用户文本清洗结果：用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】");
            ContentCleanResContext contentCleanResContext = sensitivePipelineExecutor.getContentCleanRes(
                    ContentInfoContext.builder()
                            .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .contentAttr(ContentAttr.builder().build()).build());
            return contentCleanResContext.getCleanContent();
        }, threadPoolExecutor).exceptionally(excption -> {
            /*可以感知异常，同时返回默认数据*/
            System.out.println("执行发生异常，返回默认数据，异常信息为：" + excption);
            return "";
        });
        log.info("异步任务获取用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】清洗结果:【{}】", future.get());
    }

    /**
     * 结果处理：handle
     * <p>
     * 一般与whenComplete()配合使用，异常捕获范围包含前面的所有异步线程
     * 产出型方法，即可以对正常完成的结果进行转换，也可以对异常完成的进行补偿一个结果，即可以改变阶段的现状。
     * 跟whenComplete基本一致，区别在于handle的回调方法有返回值，
     * 且handle方法返回的CompletableFuture的result是回调方法的执行结果或者回调方法执行期间抛出的异常，与原始CompletableFuture的result无关。
     */
    @Test
    public void testHandle() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8,
                16, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadCount = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-processor-" + threadCount.getAndIncrement());
            }
        });
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("异步任务获取用户文本清洗结果：用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】");
            ContentCleanResContext contentCleanResContext = sensitivePipelineExecutor.getContentCleanRes(
                    ContentInfoContext.builder()
                            .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                            .contentAttr(ContentAttr.builder().build()).build());
            return contentCleanResContext.getCleanContent();
        }, threadPoolExecutor).handle((res, excption) -> {
            /*异步方法执行完的后续处理*/
            if (excption != null) {
                log.info("执行发生异常，返回默认数据，异常信息为：" + excption);
                return "";
            }
            log.info("异步任务成功执行,上一步的结果是：" + res);
            return res + "(用于测试)";
        });
        log.info("异步任务获取用户文本【中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰】清洗结果:【{}】", future.get());
    }

}
