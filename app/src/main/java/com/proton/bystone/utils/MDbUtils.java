package com.proton.bystone.utils;

import android.os.Environment;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by Brightbeacon on 2016/7/4 0004.
 */
public class MDbUtils {
    static DbManager.DaoConfig daoConfig;

    public static DbManager.DaoConfig getDaoConfig() {
        File file = new File(Environment.getExternalStorageDirectory().getPath());
        if (daoConfig == null) {
            daoConfig = new DbManager.DaoConfig()
                    .setDbName("shiyan.db")
                    .setDbDir(file)
                    .setDbVersion(1)
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig;
    }
}
