package com.proton.bystone.utils;

import android.app.Activity;
import android.renderscript.Sampler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.proton.bystone.bean.Testd;
import com.proton.bystone.ui.main.MainActivity;
import com.proton.bystone.ui.main.tab.HomeFragment;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.id.list;
import static android.content.ContentValues.TAG;
import static com.masterfan.db.config.FlowLog.Level.V;

/**
 * Created by Administrator on 2017/6/12 0012.
 *
 */

public class UtilsOkHttp {

    public String data;
    public void  OkHttpPost(String url, Map list){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();

        Iterator it = list.keySet().iterator();

        while(it.hasNext()){
            Object key = it.next();
            String s=key.toString();
            String ss=list.get(key).toString();
            System.out.println("key = " + key + "  ;   value = " + list.get(key));
            builder.add(s,ss);
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                   Log.e("222","888");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data=response.body().string();
                getdata(data);

            }


        });

    }
    public void  getdata(String data)
    {
        Gson gson=new Gson();
        Testd testd = gson.fromJson(data, Testd.class);
        String result = testd.getResult();
        String result1 = testd.getRecords();
        System.out.print(result+"eeee"+result1);
        Log.e("111",result+result1);
    }

//用上面这
    public void  OkHttpPost2(String url,Map list){
        OkHttpClient   mOkHttpClient=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("id", "444")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("rrrrr", "rrrr");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String str = response.body().string();
                Log.i("wangshu", str);

            }

        });


    }

}
