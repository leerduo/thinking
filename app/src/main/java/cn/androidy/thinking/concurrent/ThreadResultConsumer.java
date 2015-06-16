package cn.androidy.thinking.concurrent;

/**
 * Created by Rick Meng on 2015/6/16.
 */
public interface ThreadResultConsumer {
    public void onJobComplete(ThreadJob job);
}
