package com.xxx.recommendfilm.ui.mine;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.model.notice.Notice;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class MineViewModel extends BaseViewModel {

    public MutableLiveData<UserProfile> userProfileLiveData = new MutableLiveData();
    public MutableLiveData<List<Moment>> momentListLiveData = new MutableLiveData();

    //拉取我的个人信息
    public void getUserProfile() {
        bindLife(
                apiService.getUserProfile()
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSuccess(userProfile -> userProfileLiveData.postValue(userProfile.getData()))
        );
    }

    //拉取影圈列表
    public void fetchMyMomentsList() {
        bindLife(
                apiService.fetchMyMomentList(1, 1000)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(autoProgressDialog())
                        .doOnSuccess(momentPageModelResultModel -> momentListLiveData.postValue(momentPageModelResultModel.getData().getDataList()))
        );
    }

    //发起反馈
    public void feedBack(String content, Action action) {
        bindLife(
                apiService.feedback(new Notice(content))
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(autoProgressDialog())
                        .doOnSuccess(responseBody -> {
                            action.run();
                        })
        );
    }
}
