package com.example.livedatademo.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.livedatademo.R;
import com.example.livedatademo.event.MainEvent;
import com.example.livedatademo.viewmodel.SecondViewModel;
import com.example.mvvmlibrary.base.BaseActivity;
import com.example.networkrequest.arouter.Constance;
import com.example.networkrequest.liveeventbus.LiveEventBus;
import com.example.networkrequest.utils.ToastUtil;

@Route(path = Constance.ACTIVITY_URL_SECOND)
public class SecondActivity extends BaseActivity<SecondViewModel> {

    @Override
    protected SecondViewModel getViewModel() {
        return ViewModelProviders.of(SecondActivity.this).get(SecondViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acitvity);
        ARouter.getInstance().inject(this);

        ((TextView) findViewById(R.id.text)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveEventBus.get().with("aaa").post(new MainEvent("aaa", "12345"));
                LiveEventBus.get().with("aaa").post(new MainEvent("bbb","54321"));
                finish();
            }
        });
    }

}
