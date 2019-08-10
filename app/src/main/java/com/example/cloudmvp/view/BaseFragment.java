package com.example.cloudmvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.cloudmvp.factory.PresenterFactoryImpl;
import com.example.cloudmvp.presenter.BasePresenter;
import com.example.cloudmvp.utils.Latte;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Fragment基类
 * 处理了Fragment返回事件，适用于JetPack Navigation
 * 根据Fragment的需求重写 setBackMode()-是否重写返回键,setBackPress()-具体实现的需求,postBackEvens()-通知Activity即将到来的返回事件拦截
 *
 * @author by Petterp
 * @date 2019-08-03
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView, BaseActivity.BackPressFragment {
    private P presenter = null;
    private Unbinder unbinder = null;
    private View rootView = null;

//    public abstract boolean backMode();

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Navgation 内部执行replace方法，会导致onCreateView重新执行，这里选择保存View状态(即保存数据)
        if (rootView == null) {
            if (setLayout() instanceof Integer) {
                rootView = inflater.inflate((Integer) setLayout(), container, false);
            } else if (setLayout() instanceof View) {
                rootView = (View) setLayout();
            } else {
                throw new ClassCastException("setLayout() must be int or View Error！");
            }

            if (presenter == null) {
                PresenterFactoryImpl<IBaseView, BasePresenter<IBaseView>> factory = PresenterFactoryImpl.createFactory(getClass());
                if (factory != null) {
                    presenter = (P) factory.createPresenter();
                }
            }

            if (presenter != null) {
                presenter.onAttchView(this);
                getLifecycle().addObserver(presenter);
            }

            //绑定ButterKnife
            unbinder = ButterKnife.bind(this, rootView);
            //Fragment回收保留数据
            setRetainInstance(true);
            //添加生命周期
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitleToolbar();
        //传递返回监听事件给Activity
        Latte.getBaseActivity().setiBack(this);
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
    protected P getPresenter() {
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

    /**
     * 销毁
     */
    @Override
    public void onDetachView() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        presenter = null;
        rootView = null;
    }


    /**
     * 返回事件重写,默认不重写
     */
    @Override
    public boolean setBackPress() {
        return false;
    }


    /**
     * fragment基本跳转
     * A->B
     *
     * @param id
     */
    public void fragmentStart(@IdRes int id) {
        Navigation.findNavController(getRootView()).navigate(id);
    }

    /**
     * fragment 携带数据跳转-Bundle
     * A->B
     *
     * @param id
     * @param bundle
     */
    public void fragmentStart(@IdRes int id, @Nullable Bundle bundle) {
        Navigation.findNavController(getRootView()).navigate(id, bundle);
    }

    /**
     * fragment 携带数据新写法，Navigation 标准写法
     * 需要在 nav_host中添加相应的argument，自动生成 以下类
     * 传递端 ClassName+Directions ，接收端ClassName+Args
     * <p>
     * Demo: (传递端)RegisterDelegateDirections.actionRegisterDelegateToCreateUserDelegate(phone)
     * (接收端)CreateUserDelegateArgs.fromBundle(getArguments()).getPhone()
     *
     * @param directions
     */
    public void fragmentStart(@NonNull NavDirections directions) {
        Navigation.findNavController(getRootView()).navigate(directions);
    }


    /**
     * 退栈方法
     */
    public void fragmentUP() {
        Navigation.findNavController(getRootView()).navigateUp();
    }

    /**
     * 跳转方法，多级跳转
     * A->B->C,C->A,避免Navigation 跳转后重新执行生命周期方法
     *
     * @param id
     * @return 如果退栈一次就返回true
     */
    public boolean fragmentStartToA(@IdRes int id) {
        return Navigation.findNavController(getRootView()).popBackStack(id, false);
    }

}