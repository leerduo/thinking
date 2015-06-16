package cn.androidy.thinking.concurrent;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.executor.Prioritized;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public class PrioritizedRunnable implements Runnable, Prioritized {

    private final int priority;
    private ThreadJobCallback callback;
    private String name;
    private volatile boolean isCancelled;

    public PrioritizedRunnable(ThreadJobCallback callback, String name, int priority) {
        this.callback = callback;
        this.priority = priority;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            callback.onJobComplete();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        isCancelled = true;
    }
}
