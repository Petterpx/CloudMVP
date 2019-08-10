package com.example.cloudmvp.view;


/**
 * View 基类接口
 * @author by Petterp
 * @date 2019-08-03
 */
public interface IBaseView {
    /**
     * 销毁
     */
    void onDetachView();

    /**
     * 关闭键盘
     */
    void hidekey();

}
