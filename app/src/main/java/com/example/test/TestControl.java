package com.example.test;

import android.content.Context;

import com.example.cloudmvp.model.IModel;
import com.example.cloudmvp.presenter.IPresenter;
import com.example.cloudmvp.view.IView;

/**
 * Created by Petterp
 * on 2019-11-05
 * Function:测试契约类
 */
public interface TestControl {
    interface testView extends IView {

        void test();

    }

    interface testPresenter extends IPresenter<testView, testModel> {
    }

    interface testModel extends IModel {
    }
}
