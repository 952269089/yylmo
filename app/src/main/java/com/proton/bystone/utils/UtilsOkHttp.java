package com.proton.bystone.utils;

import android.util.Log;


import java.io.IOException;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/6/12 0012.
 *
 */

public class UtilsOkHttp {

    public void  OkHttpGet(){};
    public void  OkHttpPost(){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id", "111");
        RequestBody body = builder.build();
        Request request = new Request.Builder().url("http://192.168.1.120:8080/SpringMVC/HelloController/hello9.json")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                   Log.e("222","4444");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String nex=response.body().string();

                Log.e("44444",nex);
            }


        });


    }
}
