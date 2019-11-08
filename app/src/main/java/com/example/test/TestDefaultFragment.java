package com.example.test;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cloudmvp.R;
import com.example.cloudmvp.defaultmvp.DefaultControl;
import com.example.cloudmvp.live_bus.LiveDataEventBus;
import com.example.cloudmvp.view.BaseFragment;

/**
 * Created by Petterp
 * on 2019-11-05
 * Function: 测试默认Fragment
 */
public class TestDefaultFragment extends BaseFragment<DefaultControl.DefaultPresenter> implements DefaultControl.DefaultView {
    @Override
    public Object setLayout() {
        return R.layout.test_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        getPresenter().defaultPresenter();
    }

    public static TestDefaultFragment newInstance() {

        Bundle args = new Bundle();

        TestDefaultFragment fragment = new TestDefaultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void defaultView() {

    }
}
