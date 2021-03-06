package com.example.cloudmvp.factory;


import com.example.cloudmvp.presenter.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Presenter 注解
 *
 * @author by Petterp
 * @date 2019-08-03
 */
@Inherited //可重复
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface CreatePresenter {
    Class<? extends BasePresenter> value();
}
