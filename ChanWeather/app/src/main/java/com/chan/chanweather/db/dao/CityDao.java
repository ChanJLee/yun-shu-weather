package com.chan.chanweather.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.chan.chanweather.db.wrapper.CityWrapper;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by chan on 16/3/28.
 */
public class CityDao extends AbstractDao<CityWrapper, String> {

    public static final String TABLENAME = "city";

    public static class Properties {
        public final static Property LABEL = new Property(0, String.class, "id", true, "id");
        public final static Property PROVINCE = new Property(1, String.class, "province", false, "province");
        public final static Property CITY = new Property(2, String.class, "city", false, "city");
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        final String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        final String sql = ("CREATE TABLE "+ constraint + " `$` (\n" +
                "\t`id`\tTEXT,\n" +
                "\t`province`\tTEXT,\n" +
                "\t`city`\tTEXT,\n" +
                "\tPRIMARY KEY(id)\n" +
                ");").replace("$", TABLENAME);
        db.execSQL(sql);
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"$\"";
        db.execSQL(sql.replace("$", TABLENAME));
    }

    public CityDao(DaoConfig config) {
        super(config);
    }

    public CityDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    @Override
    protected CityWrapper readEntity(Cursor cursor, int offset) {
        return new CityWrapper(
                cursor.getString(offset),
                cursor.getString(offset + 1),
                cursor.getString(offset + 2));
    }

    @Override
    protected String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset) ? null : cursor.getString(offset);
    }

    @Override
    protected void readEntity(Cursor cursor, CityWrapper entity, int offset) {
        entity.setId(cursor.getString(offset));
        entity.setProvince(cursor.getString(offset + 1));
        entity.setCity(cursor.getString(offset + 2));
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, CityWrapper entity) {
        stmt.clearBindings();

        stmt.bindString(1, entity.getId());
        stmt.bindString(2, entity.getProvince());
        stmt.bindString(3, entity.getCity());
    }

    @Override
    protected String updateKeyAfterInsert(CityWrapper entity, long rowId) {
        return null;
    }

    @Override
    protected String getKey(CityWrapper entity) {
        return entity.getId();
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }
}
