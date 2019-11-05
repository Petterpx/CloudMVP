package com.example.cloudmvp.view;


import android.content.Context;
import android.view.View;

import com.example.cloudmvp.presenter.IPresenter;

/**
 * View 基类接口
 *
 * @author by Petterp
 * @date 2019-08-03
 */
public interface IView {

    /**
     * 刷新UI时
     */
    default void updateView() {

    }

    /**
     * 获取Context
     *
     * @return
     */
    Context context();


    /**
     * 销毁
     */
    void onDetachView();

    /**
     * 关闭键盘
     */
    void hidekey();


}
