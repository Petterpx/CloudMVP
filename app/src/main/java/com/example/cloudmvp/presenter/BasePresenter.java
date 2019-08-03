package com.example.cloudmvp.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;


import com.example.cloudmvp.view.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;


/**
 * Presenter 基类
 * @author by Petterp
 * @date 2019-08-03
 */
public abstract class BasePresenter<V extends IBaseView> implements DefaultLifecycleObserver {

    private Reference<V> mview;

    public void onAttchView(V mView) {
        this.mview = new SoftReference<>(mView);
        getView(mview.get());
    }

    public abstract void getView(V view);

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.e("demo", "oncreate");
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

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (mview != null) {
            mview.get().onDetachView();
            mview.clear();
            mview = null;
        }
        owner.getLifecycle().removeObserver(this);
    }

}
