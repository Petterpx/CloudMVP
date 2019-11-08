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

    /**
     * P层接口
     */
    private P p;

    /**
     * 设置P
     * @param iPresenter
     */
    @Override
    public void setPresenter(IPresenter iPresenter) {
        this.p= (P) iPresenter;
    }

    /**
     * 获取P层接口
     * @return
     */
    @Override
    public P getPresenter() {
        return p;
    }

}
