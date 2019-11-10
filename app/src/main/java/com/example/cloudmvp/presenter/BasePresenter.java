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

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V>, InvocationHandler {

    private SoftReference mView;
    private Disposable subscribe = null;
    private M model;
    private V proxyView;


    @SuppressWarnings("unchecked")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void setView(V v) {
        this.mView = new SoftReference(v);
        proxyView = (V) Proxy.newProxyInstance(v.getClass().getClassLoader(), v.getClass().getInterfaces(), this);
        model = (M) ModelFactoryImpl.createFactory(getClass());
        if (model != null) {
            model.setPresenter(this);
        }
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return isAttached() ? method.invoke(mView.get(), args) : null;
    }

    private boolean isAttached() {
        return mView.get() != null && proxyView != null;
    }


    public V getView() {
        return proxyView;
    }

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
    public void onResume(@NonNull LifecycleOwner owner) {
        //初始化一些操作，基本操作
        initPresenter();
        if (model != null) {
            getModel().initData();
        }
    }


    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        //关闭键盘,建议添加全局Activity,这里调用公共view层的hidekey方法
        proxyView.hidekey();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        proxyView.onDetachView();
        if (mView != null) {
            mView.clear();
            mView = null;
        }

        //如果开启过EventBus，关闭
        if (isLiveBus()) {
            EventBus.clearCaches();
            EventBus.getDefault().unregister(this);
        }

        //取消Rx订阅
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
        //取消生命周期
        owner.getLifecycle().removeObserver(this);
    }


}
