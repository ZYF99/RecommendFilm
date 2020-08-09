package com.xxx.recommendfilm.ui.moment;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.model.moment.MomentPageModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import java.util.List;

public class MomentViewModel extends BaseViewModel {

    MutableLiveData<MomentPageModel> momentPageModelMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<Moment>> momentListLiveData = new MutableLiveData<>();
    private int currentPageNum = 1;
    MutableLiveData<Boolean> isLoadingMore = new MutableLiveData<>(false);
    MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>(false);

    //刷新影圈列表
    public void refreshMomentsList() {
        bindLife(
                apiService.fetchMomentList(1, 10)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSuccess(momentPageModelResultModel -> {
                            currentPageNum = 1;
                            momentPageModelMutableLiveData.postValue(momentPageModelResultModel.getData());
                            momentListLiveData.postValue(momentPageModelResultModel.getData().getDataList());
                        })
        );
    }

    //加载更多影圈列表
    public void loadMoreMomentsList() {
        final int targetPageNum = currentPageNum + 1;
        bindLife(
                apiService.fetchMomentList(targetPageNum, 10)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(this.autoProgressDialog())
                        .doOnSuccess(momentPageModelResultModel -> {
                            momentPageModelMutableLiveData.postValue(momentPageModelResultModel.getData());
                            List<Moment> newList = momentListLiveData.getValue();
                            assert newList != null;
                            newList.addAll(momentPageModelResultModel.getData().getDataList());
                            momentListLiveData.postValue(newList);
                            currentPageNum = momentPageModelResultModel.getData().getPageNum();
                        })
                        .doFinally(() -> isLoadingMore.postValue(false))
        );
    }

}
