package cn.androidy.thinking.network;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Rick Meng on 2015/6/15.
 */
public class NetworkManager {

    private static final OkHttpClient client = new OkHttpClient();
    private static ExecutorService es = Executors.newFixedThreadPool(5);

    public static void get(final String url, final Callback callback) {
        es.execute(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    callback.onSucc(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFail(e);
                }
            }
        });
    }
}
