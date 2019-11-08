package com.example.cloudmvp.live_bus;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.ArrayMap;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import java.util.Map;


/**
 * LiveData EvenBus
 */
public class LiveDataEventBus {

    private final Map<String, LiveEventData<Object>> mCacheBus;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private LiveDataEventBus() {
        mCacheBus = new ArrayMap<>();
    }

    private static class SingletonHolder {
        @SuppressLint("NewApi")
        private static final LiveDataEventBus DEFAULT_BUS = new LiveDataEventBus();
    }

    private static LiveDataEventBus get() {
        return SingletonHolder.DEFAULT_BUS;
    }

    public static MutableLiveData<Object> with(@Nullable String key) {
        return get().withInfo(key, Object.class, false);
    }

    public static <T> MutableLiveData<T> with(@Nullable String key, Class<T> type) {
        return get().withInfo(key, type, false);
    }

    public static <T> MutableLiveData<T> with(@Nullable String key, Class<T> type, boolean needCurrentDataWhenNewObserve) {
        return get().withInfo(key, type, needCurrentDataWhenNewObserve);
    }

    /**
     * 传入key，当前数据类型，及是否需要Livedata中最新数据
     * @param key
     * @param type
     * @param needData
     * @param <T>
     * @return
     */
    private   <T> MutableLiveData<T> withInfo(String key, Class<T> type, boolean needData) {
        //是否存在键值关系映射，并添加到map中
        if (!mCacheBus.containsKey(key)) {
            mCacheBus.put(key, new LiveEventData<>(key));
        }
        LiveEventData<Object> data = mCacheBus.get(key);
        if (data != null) {
            data.setmNeedCurrentDataWhenFirstObserve(needData);
        }
        return (MutableLiveData<T>) mCacheBus.get(key);
    }
}
