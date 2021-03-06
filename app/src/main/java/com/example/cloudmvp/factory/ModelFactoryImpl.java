package com.example.cloudmvp.factory;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cloudmvp.model.BaseModel;


/**
 * Created by Petterp
 * on 2019-11-04
 * Function:
 */
public class ModelFactoryImpl {
    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <M>       当前要创建的Model类型
     * @return 工厂类
     */
    @SuppressWarnings("unchecked")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static <M extends BaseModel> M createFactory(Class<?> viewClazz) {

        CreateModel annotation = viewClazz.getAnnotation(CreateModel.class);
        Class<M> aClass = null;
        if (annotation != null) {
            aClass = (Class<M>) annotation.value();
        }
        try {
            return aClass != null ? aClass.newInstance() : null;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
