package com.xxx.recommendfilm.ui.notice;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.model.notice.Notice;
import com.xxx.recommendfilm.model.notice.NoticePageModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import java.util.List;

import io.reactivex.functions.Action;

public class NoticeViewModel extends BaseViewModel {
    MutableLiveData<NoticePageModel> noticePageModelMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<Notice>> noticeListLiveData = new MutableLiveData<>();
    private int currentPageNum = 1;
    MutableLiveData<Boolean> isLoadingMore = new MutableLiveData<>(false);
    MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>(false);

    //拉取通知列表
    public void refreshNoticeList() {
        bindLife(
                apiService.fetchNoticeList(1, 10)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSuccess(noticePageModelResultModel -> {
                            currentPageNum = 1;
                            noticePageModelMutableLiveData.postValue(noticePageModelResultModel.getData());
                            noticeListLiveData.postValue(noticePageModelResultModel.getData().getDataList());
                        }).doFinally(() -> {
                            isRefreshing.postValue(false);
                        })
        );
    }

    //加载更多影圈列表
    public void loadMoreNoticeList() {
        final int targetPageNum = currentPageNum + 1;
        bindLife(
                apiService.fetchNoticeList(targetPageNum, 2)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(this.autoProgressDialog())
                        .doOnSuccess(noticePageResultModel -> {
                            noticePageModelMutableLiveData.postValue(noticePageResultModel.getData());
                            List<Notice> newList = noticeListLiveData.getValue();
                            assert newList != null;
                            newList.addAll(noticePageResultModel.getData().getDataList());
                            noticeListLiveData.postValue(newList);
                            currentPageNum = noticePageResultModel.getData().getPageNum();
                        })
                        .doFinally(() -> isLoadingMore.postValue(false))
        );
    }

}
