package org.zyf.javabasic.thread.toolstest.zyf;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/2  10:48
 */
public class ZYFLock {
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }
}
