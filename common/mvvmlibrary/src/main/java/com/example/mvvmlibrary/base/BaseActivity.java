package com.example.mvvmlibrary.base;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmlibrary.observer.ActivityLifecycleObserver;
import com.example.mvvmlibrary.widget.loadingdrawable.LoadingDialog;
import com.example.networkrequest.base.BaseActionEvent;
import com.example.networkrequest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    protected T viewModel;

    protected abstract T getViewModel();

    public LoadingDialog loadingDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewModel == null) {
            viewModel = getViewModel();
        }
        initViewModelEvent();
        getLifecycle().addObserver(new ActivityLifecycleObserver(getApplicationContext()));
    }

    protected List<ViewModel> initViewModelList() {
        return null;
    }

    private void initViewModelEvent() {
        List<ViewModel> viewModelList = initViewModelList();
        if (viewModelList != null && viewModelList.size() > 0) {
            observeEvent(viewModelList);
        } else {
            viewModel = getViewModel();
            if (viewModel != null) {
                List<ViewModel> modelList = new ArrayList<>();
                modelList.add(viewModel);
                observeEvent(modelList);
            }
        }
    }

    private void observeEvent(List<ViewModel> viewModelList) {
        for (ViewModel viewModel : viewModelList) {
            if (viewModel instanceof IViewModelAction) {
                IViewModelAction viewModelAction = (IViewModelAction) viewModel;
                viewModelAction.getActionLiveData().observe(this, baseActionEvent -> {
                    if (baseActionEvent != null) {
                        switch (baseActionEvent.getAction()) {
                            case BaseActionEvent.SHOW_LOADING_DIALOG: {
                                showLoading();
                                break;
                            }
                            case BaseActionEvent.DISMISS_LOADING_DIALOG: {
                                dismissLoading();
                                break;
                            }
                            case BaseActionEvent.SHOW_TOAST: {
                                ToastUtil.showToast(baseActionEvent.getMessage());
                                break;
                            }
                            case BaseActionEvent.FINISH: {
                                finish();
                                break;
                            }
                            case BaseActionEvent.FINISH_WITH_RESULT_OK: {
                                setResult(RESULT_OK);
                                finish();
                                break;
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel.mRepository != null) {
            viewModel.mRepository.dispose();
        }
        dismissLoading();
    }

    protected void showLoading() {

        if(loadingDialog == null) {
            loadingDialog = new LoadingDialog.Builder(this)
                    .setCancelOutside(false)
                    .setCancelable(false)
                    .create();
            loadingDialog.show();
        }

    }

    protected void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
