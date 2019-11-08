package com.example.cloudmvp.live_bus;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

/**
 * Created by Petterp
 * on 2019-11-05
 * Function: 自定义的一个观察者包装类
 */
public class ObserverWrapper<T> implements Observer<T> {

    private Observer<T> observer;
    private LiveEventData<T> liveData;

    private ObserverWrapper(Observer<T> observer, LiveEventData<T> liveData) {
        this.observer = observer;
        this.liveData = liveData;
        //mIsStartChangeData 可过滤掉liveData首次创建监听，之前的遗留的值
        liveData.setmIsStartChangeData();
    }

    @Override
    public void onChanged(@Nullable T t) {
        if (liveData.ismIsStartChangeData()) {
            if (observer != null) {
                observer.onChanged(t);
            }
        }
    }
}