package com.chan.chanweather.db.session;

import android.database.sqlite.SQLiteDatabase;

import com.chan.chanweather.db.dao.CityDao;
import com.chan.chanweather.db.wrapper.CityWrapper;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by chan on 16/3/27.
 */
public class DaoSession extends AbstractDaoSession {
    private DaoConfig m_cityConfig;
    private CityDao m_cityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType session, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap) {
        super(db);

        m_cityConfig = daoConfigMap.get(CityDao.class).clone();
        m_cityConfig.initIdentityScope(session);
        m_cityDao = new CityDao(m_cityConfig, this);
        registerDao(CityWrapper.class, m_cityDao);
    }

    public void clear() {
        m_cityConfig.getIdentityScope().clear();
    }

    public CityDao getCityDao() {
        return m_cityDao;
    }
}
