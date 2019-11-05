package com.example.cloudmvp.defaultmvp;

import com.example.cloudmvp.model.IModel;
import com.example.cloudmvp.presenter.IPresenter;
import com.example.cloudmvp.view.IView;

/**
 * Created by Petterp
 * on 2019-11-05
 * Function: 默认控制器
 */
public interface DefaultControl {

    interface DefaultView extends IView {
        /**
         * 默认view操作
         */
        void defaultView();
    }


    interface DefaultModel extends IModel {
        /**
         * 默认model
         */
        void defaultModel();
    }

    interface DefaultPresenter extends IPresenter<DefaultView, DefaultModel> {
        /**
         * 默认Presenter
         */
        void defaultPresenter();
    }

}
