package com.chan.chanweather.db.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.chan.chanweather.db.dao.CityDao;
import com.chan.chanweather.db.master.DaoMaster;
import com.chan.chanweather.db.session.DaoSession;

/**
 * Created by chan on 16/3/29.
 */
public class CityDB {

    private static final String DB_NAME = "chan_db";

    public static CityDao getCityDao(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        CityDao.createTable(sqLiteDatabase, true);

        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getCityDao();
    }
}
