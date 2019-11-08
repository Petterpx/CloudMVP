package com.example.cloudmvp.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.cloudmvp.factory.CreatePresenter;
import com.example.cloudmvp.factory.PresenterFactoryImpl;
import com.example.cloudmvp.presenter.IPresenter;
import com.example.cloudmvp.utils.Latte;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * @author by Petterp
 * @date 2019-08-03
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView{
    private P presenter = null;
    private Unbinder unbinder = null;
    private View rootView = null;
    /**
     * 设置view
     *
     * @return view
     */
    public abstract Object setLayout();


    /**
     * 创建视图
     *
     * @param savedInstanceState
     * @param rootView
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() must be int or View Error！");
        }

        if (presenter == null) {
            presenter = (P) PresenterFactoryImpl.createFactory(getClass());
        }

        if (presenter != null) {
            presenter.setView(this);
            getLifecycle().addObserver(presenter);
        }

        //绑定ButterKnife
        unbinder = ButterKnife.bind(this, rootView);
        //Fragment回收保留数据
        setRetainInstance(true);
        //添加生命周期
        onBindView(savedInstanceState, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitleToolbar();
    }

    /**
     * 沉浸式状态栏
     */
    private void setTitleToolbar() {
        ImmersionBar.with(this)
                .titleBar(setToolbar())
                .autoDarkModeEnable(true)
                .init();
    }

    @Override
    public Context context() {
        return getContext();
    }

    /**
     * 设置Toolbar
     *
     * @return
     */
    public View setToolbar() {
        return null;
    }

    /**
     * 返回P
     *
     * @return Presenter
     */
    public P getPresenter() {
        return presenter;
    }

    /**
     * 返回子类View
     *
     * @return view
     */
    protected View getRootView() {
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        presenter = null;
        rootView = null;
    }


    @Override
    public void hidekey() {

    }


    @Override
    public void showLoader() {

    }

    @Override
    public void stopLoader() {

    }
}
