package com.example.login.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;

import com.example.login.repository.LoginRepository;
import com.example.mvvmlibrary.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel<LoginRepository> {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
}
