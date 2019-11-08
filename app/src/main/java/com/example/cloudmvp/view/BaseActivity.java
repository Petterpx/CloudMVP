package com.example.cloudmvp.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cloudmvp.utils.Latte;


/**
 * 抽象Activity基类，包含Fragment返回键处理
 *
 * @author by petterp
 * @date 2019-08-07
 */
public abstract class BaseActivity extends AppCompatActivity {
    private BackPressFragment iBack;

    public interface BackPressFragment {
        /**
         * 相应逻辑方法
         *
         * @return
         */
        boolean setBackPress();
    }

    public void setiBack(BackPressFragment back) {
        this.iBack = back;
    }


    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //添加配置信息,适用于单Activity，默认配置,如果需要自行配置，请重写setLatteinit()返回值
        if (setLatteinit()) {
            Latte.getConfigurator().withBaseActivity(this);
        } else {
            Latte.getConfigurator()
                    .withContext(this)
                    .withBaseActivity(this)
                    .withHandler(new Handler())
                    .configure();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //执行相应方法，成功拦截，否则默认执行
        if (iBack!=null){
            if (iBack.setBackPress()) {
                return true;
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    public boolean setLatteinit() {
        return false;
    }
}
