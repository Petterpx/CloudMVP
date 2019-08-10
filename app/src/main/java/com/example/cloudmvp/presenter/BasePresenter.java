package com.example.cloudmvp.presenter;


import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;


import com.example.cloudmvp.view.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * P层基类
 * 使用RxJava 进行数据的读取，然后刷新ui.
 *
 * @author by Petterp
 * @date 2019-08-03
 */
public abstract class BasePresenter<V extends IBaseView> implements DefaultLifecycleObserver {

    private Reference<V> mView;
    private Disposable subscribe;

    public void onAttchView(V view) {
        this.mView = new SoftReference<>(view);
        getView(mView.get());
    }

    public abstract void getView(V view);

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (startRxMode()) {
            startRxData();
        }
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        //关闭键盘,建议添加全局Activity,这里调用公共view层的hidekey方法
        if (mView != null) {
            mView.get().hidekey();
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (mView != null) {
            mView.get().onDetachView();
            mView.clear();
            mView = null;
        }
        //取消Rx订阅
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
        //取消生命周期
        owner.getLifecycle().removeObserver(this);
    }

    public void startRxData() {
        subscribe = Observable
                .create(emitter -> {
                    rxPostData();
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::rxGetData)
                .subscribe();
    }


    /**
     * 是否需要Rx预加载数据
     *
     * @return 结果
     */
    public boolean startRxMode() {
        return false;
    }

    /**
     * Rx开始预加载数据
     */
    public void rxPostData() {

    }

    /**
     * Rx数据加载完成
     */
    public void rxGetData() {

    }


}