package com.example.main.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.main.R;
import com.example.main.R2;
import com.example.main.viewmodel.MainViewModel;
import com.example.mvvmlibrary.base.BaseActivity;
import com.example.networkrequest.arouter.Constance;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = Constance.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity<MainViewModel> {

    @BindView(R2.id.btn)
    Button btn1;
    @BindView(R2.id.btn2)
    Button btn2;

    @Override
    protected MainViewModel getViewModel() {
        return ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        viewModel.getBooleanMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                btn1.setText("Boolean");
            }

        });

    }


    @OnClick({R2.id.btn, R2.id.btn2})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn) {
            ARouter.getInstance().build(Constance.ACTIVITY_URL_LOGIN).navigation();
        } else if (i == R.id.btn2) {
            viewModel.getUserInfo("17521316212");
        }
    }
}
