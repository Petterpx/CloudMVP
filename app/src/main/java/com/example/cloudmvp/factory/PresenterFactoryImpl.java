package com.example.cloudmvp.factory;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cloudmvp.presenter.BasePresenter;

/**
 * 注解工厂
 *
 * @author by Petterp
 * @date 2019-08-03
 */
public class PresenterFactoryImpl {

    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <P>       当前要创建的Presenter类型
     * @return 工厂类
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static <P extends BasePresenter> P createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        try {
            return aClass != null ? aClass.newInstance() : null;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }

}
