package com.example.test;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cloudmvp.R;
import com.example.cloudmvp.factory.CreatePresenter;
import com.example.cloudmvp.view.BaseFragment;

/**
 * Created by Petterp
 * on 2019-11-05
 * Function: 测试Fragment
 */
@CreatePresenter(TestPresenter.class)
public class TestFragment extends BaseFragment<TestControl.testPresenter> implements TestControl.testView {
    @Override
    public Object setLayout() {
        return R.layout.test_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void updateView() {

    }
}
