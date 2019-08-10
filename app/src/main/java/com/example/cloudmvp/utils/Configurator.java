package com.example.cloudmvp.utils;
import android.content.Context;
import android.os.Handler;

import com.example.cloudmvp.view.BaseActivity;

import java.util.HashMap;


/**
 * Auther: Petterp on 2019/4/14
 * Summary:配置文件的存储与获取
 */
public class Configurator {
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();

    private Configurator() {
    }

    public final void configure() {
        //配置完成时调用
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    /**
     * 静态内部类单例模式，多线程安全
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 静态单例
     *
     * @return
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 存储配置信息的键值对
     *
     * @return
     */
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 检查配置项是否完成
     * //如果配置未完成，抛出运行时异常
     * if (!isReady){
     * throw  new RuntimeException("Configuration is not ready,call configure");
     * }
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        //如果配置未完成，抛出运行时异常
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 获取相应的信息
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }


    /**
     * 全局Activity
     *
     * @param activity
     * @return
     */
    public final Configurator withBaseActivity(BaseActivity activity) {
        LATTE_CONFIGS.put(ConfigKeys.BASEACTIVITY, activity);
        return this;
    }

    /**
     * 全局Handler
     * @param handler
     * @return
     */
    public final Configurator withHandler(Handler handler) {
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, handler);
        return this;
    }

    /**
     * 全局Handler
     * @param context
     * @return
     */
    public final Configurator withContext(Context context) {
        LATTE_CONFIGS.put(ConfigKeys.CONTEXT, context);
        return this;
    }

}
