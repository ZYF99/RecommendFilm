package com.xxx.recommendfilm.ui.base;

import androidx.lifecycle.ViewModel;
import com.xxx.recommendfilm.manager.api.ApiService;
import com.xxx.recommendfilm.manager.di.component.DaggerAppComponent;

import javax.inject.Inject;

public class BaseViewModel extends ViewModel {

    @Inject
    public ApiService apiService;

    public BaseViewModel() {
        DaggerAppComponent.create().inject(this);
    }

}
