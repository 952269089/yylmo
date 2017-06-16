package com.proton.bystone.cache;

import com.masterfan.db.annotation.Database;

/**
 * Created by Brightbeacon on 2016/7/4 0004.
 */
@Database(name = DB.NAME, version = DB.VERSION)
public class DB {
    public static final String NAME = "bystone_db";
    public static final int    VERSION = 1;
}
