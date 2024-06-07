package org.zyf.javabasic.thread.lock.opti;

/**
 * @program: zyfboot-javabasic
 * @description: LockPerformanceTest
 * @author: zhangyanfeng
 * @create: 2024-06-08 01:24
 **/
public class LockTimePerformanceTest {
    private static final int NUM_THREADS = 10;
    private static final int NUM_ITERATIONS = 1000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("UnoptimizedCounter Performance Test");
        UnoptimizedCounter unoptimizedCounter = new UnoptimizedCounter();
        testCounterPerformance(unoptimizedCounter);

        System.out.println("OptimizedCounter Performance Test");
        OptimizedCounter optimizedCounter = new OptimizedCounter();
        testCounterPerformance(optimizedCounter);
    }

    private static void testCounterPerformance(Object counter) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    if (counter instanceof UnoptimizedCounter) {
                        ((UnoptimizedCounter) counter).increment();
                    } else if (counter instanceof OptimizedCounter) {
                        ((OptimizedCounter) counter).increment();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        if (counter instanceof UnoptimizedCounter) {
            System.out.println("Final count (Unoptimized): " + ((UnoptimizedCounter) counter).getCount());
        } else if (counter instanceof OptimizedCounter) {
            System.out.println("Final count (Optimized): " + ((OptimizedCounter) counter).getCount());
        }
    }
}
