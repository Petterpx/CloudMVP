package com.example.test;


import com.example.cloudmvp.factory.CreateModel;
import com.example.cloudmvp.presenter.BasePresenter;

/**
 * Created by Petterp
 * on 2019-11-05
 * Function: 测试P
 */
@CreateModel(TestModels.class)
public class TestPresenter extends BasePresenter<TestControl.testView, TestControl.testModel> implements TestControl.testPresenter {
    @Override
    public void initPresenter() {
        getView().test();
    }
}
