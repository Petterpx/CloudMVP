package com.example.cloudmvp.model;


import com.example.cloudmvp.presenter.IPresenter;

/**
 * Created by Petterp
 * on 2019-10-19
 * Function: Model 接口
 */
public interface IModel<P extends IPresenter> {

    /**
     * 某些初始化行为
     */
    default void initData() {

    }


    /**
     * 设置P
     * @param p
     */
    void setPresenter(P p);

    /**
     * 初始化耗时行为
     */
    default void RxModelinit() {

    }

    /**
     * 获取P层对象
     *
     * @return P
     */
    P getPresenter();
}
