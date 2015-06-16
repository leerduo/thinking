package cn.androidy.thinking.concurrent;

import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class MyFifoPriorityThreadPoolExecutor extends FifoPriorityThreadPoolExecutor {
    public MyFifoPriorityThreadPoolExecutor(int poolSize) {
        super(poolSize);
    }

    public MyFifoPriorityThreadPoolExecutor(int poolSize, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        super(poolSize, uncaughtThrowableStrategy);
    }

    public MyFifoPriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAlive, TimeUnit timeUnit, ThreadFactory threadFactory, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        super(corePoolSize, maximumPoolSize, keepAlive, timeUnit, threadFactory, uncaughtThrowableStrategy);
    }
}
