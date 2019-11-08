package com.example.cloudmvp.presenter;

import androidx.lifecycle.DefaultLifecycleObserver;

import com.example.cloudmvp.model.IModel;
import com.example.cloudmvp.view.IView;

/**
 * Created by Petterp
 * on 2019-11-04
 * Function:Presenter 接口
 */
public interface IPresenter<V extends IView, M extends IModel> extends DefaultLifecycleObserver {

    /**
     * 一些初始化的操作
     */
    default void initPresenter() {

    }

    /**
     * 是否需要LiveBus-> 暂时为EventBus
     *
     * @return
     */
    default boolean isLiveBus() {
        return false;
    }

    /**
     * 设置View
     *
     * @param v
     */
    void setView(V v);

    /**
     * 获取V
     *
     * @return V
     */
    V getView();

    /**
     * 获取M
     *
     * @return M
     */
    M getModel();

    /**
     * 启动Rx初始化耗时任务
     */
    void rxStartInitData();

    /**
     * Rx任务结束时
     */
    default void rxEndInitData() {

    }

    /**
     * rx初始化时
     */
    default void rxSpecificData() {

    }

    /**
     * 是否需要Rx
     *
     * @return mode
     */
    default boolean rxMode() {
        return false;
    }

}
