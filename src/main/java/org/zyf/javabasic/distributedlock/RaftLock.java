//package org.zyf.javabasic.distributedlock;
//
//import io.atomix.cluster.MemberId;
//import io.atomix.core.Atomix;
//import io.atomix.core.lock.DistributedLock;
//import io.atomix.protocols.raft.partition.RaftPartitionGroup;
//import io.atomix.utils.net.Address;
//
//import java.io.File;
//import java.time.Duration;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
///**
// * @author yanfengzhang
// * @description 使用 Java 实现的简化版基于 Raft 协议的分布式锁的示例代码
// * @date 2023/4/24  23:32
// */
//public class RaftLock implements AutoCloseable {
//    private final Atomix atomix;
//    private final DistributedLock lock;
//
//    public RaftLock(String clusterId, MemberId memberId, Address address, String lockName, MemberId[] members) {
//        // 创建 Raft 分区组
//        RaftPartitionGroup raftPartitionGroup = RaftPartitionGroup.builder("raft")
//                .withNumPartitions(1)
//                .withMembers(members)
//                .withDataDirectory(new File("data"))
//                .withFlushOnCommit()
//                .build();
//
//        // 创建 Atomix 实例
//        atomix = Atomix.builder()
//                .withClusterId(clusterId)
//                .withMemberId(memberId)
//                .withAddress(address)
//                .withMembershipProvider(raftPartitionGroup.getMembershipProvider())
//                .withPartitionGroups(raftPartitionGroup)
//                .build();
//
//        // 启动 Atomix 实例
//        atomix.start().join();
//
//        // 获取分布式锁
//        lock = atomix.getLock(lockName).join();
//    }
//
//    public boolean tryLock(Duration timeout) throws ExecutionException, InterruptedException {
//        return lock.tryLock(timeout).get();
//    }
//
//    public CompletableFuture<Void> unlock() {
//        return lock.unlock();
//    }
//
//    @Override
//    public void close() {
//        // 关闭 Atomix 实例
//        atomix.stop().join();
//    }
//
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // 创建 RaftLock 实例
//        RaftLock lock = new RaftLock("raft-cluster", MemberId.from("member1"), Address.from("localhost", 8701),
//                "test-lock", new MemberId[]{MemberId.from("member1"), MemberId.from("member2"), MemberId.from("member3")});
//
//        // 尝试获取锁
//        boolean isLocked = lock.tryLock(Duration.ofSeconds(10));
//        if (isLocked) {
//            try {
//                // 执行业务逻辑
//                System.out.println("Lock acquired, do something...");
//            } finally {
//                // 释放锁
//                lock.unlock().join();
//            }
//        } else {
//            System.out.println("Failed to acquire lock");
//        }
//
//        // 关闭 RaftLock 实例
//        lock.close();
//    }
//}
