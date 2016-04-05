package com.chan.chanweather.model.rx;

import android.content.Context;

import com.chan.chanweather.db.core.CityDB;
import com.chan.chanweather.db.dao.CityDao;
import com.chan.chanweather.db.wrapper.CityWrapper;
import com.chan.chanweather.injector.annotation.ContextLife;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import de.greenrobot.dao.query.Query;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by chan on 16/3/29.
 */
public class RxReadSettingItems {

    private Context m_context;

    public RxReadSettingItems(@ContextLife("application") Context context) {
        m_context = context;
    }

    public Observable<List<String>> readProvinceAsync() {
        return readProvinceSync().subscribeOn(Schedulers.newThread());
    }

    public Observable<List<String>> readProvinceSync() {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {

            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                CityDao cityDao = CityDB.getCityDao(m_context);
                List<CityWrapper> cityWrapperList = cityDao.queryRaw("");
                Set<String> set = new HashSet<String>();
                final int size = cityWrapperList.size();
                for (int i = 0; i < size; ++i) {
                    set.add(cityWrapperList.get(i).getProvince());
                }

                Iterator<String> iterator = set.iterator();
                List<String> list = new ArrayList<String>();
                while (iterator.hasNext()) {
                    list.add(iterator.next());
                }
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<String>> readCityAsync(String province) {
        return readCitySync(province).subscribeOn(Schedulers.newThread());
    }

    public Observable<List<String>> readCitySync(final String province) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                CityDao cityDao = CityDB.getCityDao(m_context);
                Query<CityWrapper> query = cityDao.queryBuilder()
                        .where(CityDao.Properties.PROVINCE.eq(province)).build();
                List<CityWrapper> cityWrapperList = query.list();
                List<String> cities = new ArrayList<String>();
                try {
                    final int size = cityWrapperList.size();
                    for (int i = 0; i < size; ++i) {
                        cities.add(cityWrapperList.get(i).getCity());
                    }
                    subscriber.onNext(cities);
                } catch (Exception e) {
                    subscriber.onError(e);
                    return;
                }
                subscriber.onCompleted();
            }
        }).onBackpressureDrop();
    }

    public Observable<String> queryCityIdAsync(String province, String city) {
        return queryCityIdSync(province, city).subscribeOn(Schedulers.newThread());
    }

    public Observable<String> queryCityIdSync(final String province, final String city) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                CityDao cityDao = CityDB.getCityDao(m_context);
                Query<CityWrapper> query = cityDao.queryBuilder().where(CityDao.Properties.PROVINCE.eq(province),
                        CityDao.Properties.CITY.eq(city)).build();
                List<CityWrapper> cityWrapperList = query.list();
                subscriber.onNext(cityWrapperList.get(0).getId());
                subscriber.onCompleted();
            }
        });
    }
}
