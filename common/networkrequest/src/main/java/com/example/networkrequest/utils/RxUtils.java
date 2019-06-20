package com.example.networkrequest.utils;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.networkrequest.base.BaseResponse;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {
    public RxUtils() {
    }

    public static LifecycleTransformer bindToLifecycle(@NonNull Context lifecycle) {
        if (lifecycle instanceof LifecycleProvider) {
            return ((LifecycleProvider)lifecycle).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("context not the LifecycleProvider type");
        }
    }

    public static <T> LifecycleTransformer bindToLifecycle(@NonNull Fragment lifecycle) {
        if (lifecycle instanceof LifecycleProvider) {
            return ((LifecycleProvider)lifecycle).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("fragment not the LifecycleProvider type");
        }
    }

    public static <T> LifecycleTransformer bindToLifecycle(@NonNull LifecycleProvider lifecycle) {
        return lifecycle.bindToLifecycle();
    }

    public static ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer() {
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<BaseResponse<T>, T> exceptionTransformer() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            public ObservableSource<T> apply(Observable observable) {
                return observable.onErrorResumeNext(new RxUtils.HttpResponseFunc());
            }
        };
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        private HttpResponseFunc() {
        }

        public Observable<T> apply(Throwable t) {
            return Observable.error(t);
        }
    }
}
