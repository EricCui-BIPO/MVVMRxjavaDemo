package com.example.livedatademo.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.livedatademo.repository.MainRepository;
import com.example.mvvmlibrary.base.BaseViewModel;

public class SecondViewModel extends BaseViewModel<MainRepository> {

    public SecondViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected MainRepository getRepository() {
        return new MainRepository(this);
    }

}
