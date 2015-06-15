package cn.androidy.thinking.network;

/**
 * Created by Rick Meng on 2015/6/15.
 */
public interface Callback {
    public void onSucc(String result);
    public void onFail(Throwable t);
}
