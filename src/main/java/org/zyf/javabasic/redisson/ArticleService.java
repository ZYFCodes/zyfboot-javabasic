package org.zyf.javabasic.redisson;

import org.redisson.Redisson;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @program: zyfboot-javabasic
 * @description: 使用Redisson的ReadWriteLock的业务场景
 * @author: zhangyanfeng
 * @create: 2023-10-03 15:28
 **/
public class ArticleService {
    private static final String ARTICLE_LOCK_KEY = "article:lock";
    private static final String ARTICLE_CONTENT_KEY = "article:content";

    public static void main(String[] args) {
        // 创建Redisson客户端
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");

        RedissonClient redisson = Redisson.create(config);

        // 获取读写锁
        RReadWriteLock rwLock = redisson.getReadWriteLock(ARTICLE_LOCK_KEY);

        try {
            // 获取读锁
            rwLock.readLock().lock();

            // 读取文章内容
            String articleContent = getArticleContent();
            System.out.println("文章内容：" + articleContent);

            // 模拟读取操作耗时
            Thread.sleep(1000);

            // 释放读锁
            rwLock.readLock().unlock();

            // 获取写锁
            rwLock.writeLock().lock();

            // 编辑和发布文章
            editAndPublishArticle();
            System.out.println("文章编辑和发布成功");

            // 释放写锁
            rwLock.writeLock().unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭Redisson客户端
            redisson.shutdown();
        }
    }

    private static String getArticleContent() {
        // 模拟从数据库或缓存中获取文章内容的操作
        return "这是一篇文章的内容";
    }

    private static void editAndPublishArticle() {
        // 模拟编辑和发布文章的操作
    }
}
