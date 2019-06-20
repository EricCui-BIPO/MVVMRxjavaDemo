package com.example.login.view;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.login.R;
import com.example.login.viewmodel.LoginViewModel;
import com.example.mvvmlibrary.base.BaseActivity;
import com.example.networkrequest.arouter.Constance;

@Route(path = Constance.ACTIVITY_URL_LOGIN)
public class LoginActivity extends BaseActivity<LoginViewModel> {

    @Override
    protected LoginViewModel getViewModel() {
        return ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ARouter.getInstance().inject(this);
    }
}
