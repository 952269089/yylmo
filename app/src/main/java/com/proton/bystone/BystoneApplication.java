package com.proton.bystone;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.proton.bystone.net.HttpClients;

import org.xutils.x;


/**
 * Created by Brightbeacon on 2016/7/4 0004.
 */
public class BystoneApplication extends Application {
    private static BystoneApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//xutils init
        HttpClients.getInstance().initialize(this);

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(getApplicationContext());
        ImageLoader.getInstance().init(configuration);
    }

    public static BystoneApplication getInstance(){
        // 这里不用判断instance是否为空
        return instance;
    }
}
