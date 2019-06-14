package com.example.livedatademo.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.livedatademo.R;
import com.example.livedatademo.event.MainEvent;
import com.example.livedatademo.viewmodel.MainViewModel;
import com.example.mvvmlibrary.base.BaseActivity;
import com.example.mvvmlibrary.widget.loadingdrawable.LoadingDialog;
import com.example.networkrequest.arouter.Constance;
import com.example.networkrequest.liveeventbus.LiveEventBus;
import com.example.networkrequest.utils.ToastUtil;

@Route(path = Constance.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity<MainViewModel> {

    TextView text;
    public  static String key = "c3f9d6c4c70559205cab02fb9f8d4a66";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);

        LiveEventBus.get()
                .with("aaa", MainEvent.class)
                .observe(this,observer);

        Button mainFragmentBtn = (Button)findViewById(R.id.main_fragment_btn);
        text = (TextView)findViewById(R.id.text);
        mainFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getUserInfo("17521316212");
//                ARouter.getInstance().build(Constance.ACTIVITY_URL_SECOND).navigation();
            }
        });


        viewModel.getBooleanMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                text.setText("Boolean");
            }

        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadingDialog.Builder(MainActivity.this)
                        .setCancelOutside(false)
                        .setCancelable(false)
                        .create().show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected MainViewModel getViewModel() {
        return ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private Observer<MainEvent> observer = new Observer<MainEvent>() {
        @Override
        public void onChanged(@Nullable MainEvent event) {
            if (event.getType().equals("aaa")){
                text.setText(event.getMsg());
            } else if ("bbb".equals(event.getType())){
                ToastUtil.showToast(event.getMsg());
            }
        }
    };

}
