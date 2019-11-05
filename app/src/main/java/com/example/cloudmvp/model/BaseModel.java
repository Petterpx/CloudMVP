package com.example.cloudmvp.model;

import androidx.lifecycle.MutableLiveData;

import com.example.cloudmvp.presenter.BasePresenter;
import com.example.cloudmvp.presenter.IPresenter;

/**
 * Created by Petterp
 * on 2019-10-19
 * Function: Model超类
 */
public abstract class BaseModel<P extends IPresenter> implements IModel {

    private P p;

    @Override
    public void setPresenter(IPresenter iPresenter) {
        this.p= (P) iPresenter;
    }

    @Override
    public P getPresenter() {
        return p;
    }


}
