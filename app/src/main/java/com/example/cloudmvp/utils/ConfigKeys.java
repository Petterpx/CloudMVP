package com.example.cloudmvp.utils;

/**
 * Auther: Petterp on 2019/4/14
 * Summary:用枚举来做安全的单例(惰性单例),整个程序只会被初始化一次
 */
public enum ConfigKeys {
    CONTEXT,        //全局上下文
    BASEACTIVITY,            //全局Activity
    CONFIG_READY,       //控制初始化是否完成
    HANDLER
}
