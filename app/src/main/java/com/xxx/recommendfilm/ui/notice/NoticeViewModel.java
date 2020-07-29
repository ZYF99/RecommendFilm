package com.xxx.recommendfilm.ui.notice;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.model.notice.Notice;
import com.xxx.recommendfilm.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class NoticeViewModel extends BaseViewModel {
    MutableLiveData<List<Notice>> noticeList = new MutableLiveData<>();

    //拉取通知列表
    public void fetchMoticeList() {
        List<Notice> notices = new ArrayList<>();
        notices.add(new Notice());
        notices.add(new Notice());
        notices.add(new Notice());
        notices.add(new Notice());
        notices.add(new Notice());
        notices.add(new Notice());
        noticeList.postValue(notices);
    }

}
