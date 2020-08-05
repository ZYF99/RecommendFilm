package com.xxx.recommendfilm.ui.release;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.ui.base.BaseViewModel;

public class ReleaseViewModel extends BaseViewModel {
    public MutableLiveData<String> inputContentData = new MutableLiveData();
    public MutableLiveData<String> imgUrlLiveData = new MutableLiveData();
}
