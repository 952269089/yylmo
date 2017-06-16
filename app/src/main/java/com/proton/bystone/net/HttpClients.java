package com.proton.bystone.net;

import android.content.Context;
import android.text.TextUtils;

import com.proton.bystone.bean.Resp;
import com.squareup.okhttp.OkHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by 13510 on 2016/4/1.
 */
public class HttpClients {

    private static final String BASE_URL = "http://www.kuaidi100.com/";//开发服务器

    private Context mContext;
    private RestAdapter restAdapter = null;
    private NetInterface netInterface = null;

    private static HttpClients instance;

    private HttpClients() {
    }

    public static HttpClients getInstance() {
        if (instance == null) {
            instance = new HttpClients();
        }
        return instance;
    }

    /**
     * @param context 上下文
     * @return false:初期化失败 true:初期化成功
     */
    public boolean initialize(Context context) {
        mContext = context;
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS);//连接超时
        client.setReadTimeout(30, TimeUnit.SECONDS);   //读取超时

        // 创建适配器
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

//                        // 设置Header信息
//                        request.addHeader("Set-Cookie", "TICKET=" + temp);
                    }
                }).build();
        if (restAdapter == null) {
            return false;
        }
        netInterface = restAdapter.create(NetInterface.class);
        return netInterface != null;
    }

    //接口
    interface NetInterface {

        @GET("/query")
        void getTestData(@Query("type") String type, @Query("postid") String postid, Callback<Resp> cb);//获取首页广告
    }

    /**
     * 获取首页广告
     *
     * @param cb
     */
    public void getTestData(String type, String postid, Callback<Resp> cb) {
        netInterface.getTestData(type, postid, cb);
    }

}
