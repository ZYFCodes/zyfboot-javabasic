package org.zyf.javabasic.thread.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  20:05
 */
public class GoodsComments {
    private Map<Long, List<Comment>> commentsMap = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void addComment(Long goodsId, Comment comment) {
        rwLock.writeLock().lock();
        try {
            List<Comment> comments = commentsMap.get(goodsId);
            if (comments == null) {
                comments = new ArrayList<>();
                commentsMap.put(goodsId, comments);
            }
            comments.add(comment);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public List<Comment> getComments(Long goodsId) {
        rwLock.readLock().lock();
        try {
            return commentsMap.get(goodsId);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    //其他方法省略...

    class Comment {
    }
}
