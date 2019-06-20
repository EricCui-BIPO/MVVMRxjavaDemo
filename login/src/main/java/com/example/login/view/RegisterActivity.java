package com.example.login.view;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.login.R;
import com.example.login.viewmodel.LoginViewModel;
import com.example.mvvmlibrary.base.BaseActivity;
import com.example.networkrequest.arouter.Constance;

@Route(path = Constance.ACTIVITY_URL_REGISTER)
public class RegisterActivity extends BaseActivity<LoginViewModel>{

    @Override
    protected LoginViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
