package com.example.cloudmvp.presenter;


import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;


import com.example.cloudmvp.factory.CreateModel;
import com.example.cloudmvp.factory.CreatePresenter;
import com.example.cloudmvp.factory.ModelFactoryImpl;
import com.example.cloudmvp.model.IModel;
import com.example.cloudmvp.view.IView;

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
@CreateModel
public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V, M> {

    private Reference<V> mView;
    private Disposable subscribe = null;
    private M model;

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (rxMode()) {
            rxStartInitData();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void setView(V v) {
        this.mView = new SoftReference<>(v);
        model = (M) ModelFactoryImpl.createFactory(getClass());
        assert model != null;
        model.setPresenter(this);
    }

    @Override
    public V getView() {
        return mView.get();
    }

    @Override
    public M getModel() {
        return model;
    }


    @Override
    public void rxStartInitData() {
        subscribe = Observable
                .create(emitter -> {
                    rxSpecificData();
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::rxEndInitData)
                .subscribe();
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


}
