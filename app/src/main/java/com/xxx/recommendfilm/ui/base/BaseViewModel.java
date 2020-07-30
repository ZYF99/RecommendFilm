package com.xxx.recommendfilm.ui.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import com.xxx.recommendfilm.MainApplication;
import com.xxx.recommendfilm.manager.api.ApiService;
import javax.inject.Inject;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class BaseViewModel extends ViewModel implements BindLife, LifecycleObserver {

    @Inject
    public ApiService apiService;

    protected MutableLiveData<Boolean> isShowLoadingProgress = new MutableLiveData(false);

    public BaseViewModel() {
        MainApplication.getApplication().appComponent.inject(this);
    }

    protected <T> void bindLife(Single<T> single) {
        compositeDisposable.add(single.subscribe(new Consumer<T>() {
            @Override
            public void accept(T t) {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                //Log.d("RxError", throwable.getMessage());
            }
        }));
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        compositeDisposable.dispose();
        compositeDisposable.clear();
    }

    protected <T> SingleTransformer<T, T> autoProgressDialog() {
        return new SingleTransformer<T, T>() {
            @Override
            public SingleSource<T> apply(Single<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        isShowLoadingProgress.postValue(true);
                    }
                }).doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        isShowLoadingProgress.postValue(false);
                    }
                });
            }
        };
    }
}
