package com.example.cloudmvp.factory;


import com.example.cloudmvp.model.BaseModel;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Petterp
 * on 2019-11-04
 * Function:
 */
@Inherited //可重复
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface CreateModel {
    Class<? extends BaseModel> value();
}
