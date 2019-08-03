package com.example.cloudmvp.factory;


import com.example.cloudmvp.presenter.BasePresenter;
import com.example.cloudmvp.view.IBaseView;

/**
 * Presenter工厂接口 ->创建Presenter
 * @author by Petterp
 * @date 2019-08-03
 */
public interface PresenterFactory <V extends IBaseView,P extends BasePresenter<V>>{


    /**
     * 创建Presenter
     * @return p(presenter)
     */
    P createPresenter();

}
