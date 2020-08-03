package com.xxx.recommendfilm.ui.mine;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.mine.UserProfile;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.functions.Consumer;

public class MineViewModel extends BaseViewModel {

    public MutableLiveData<UserProfile> userProfileLiveData = new MutableLiveData();
    public MutableLiveData<List<Moment>> momentListLiveData = new MutableLiveData();

    //拉取我的个人信息
    public void getUserProfile() {
        bindLife(
                apiService.getUserProfile()
                        .compose(RxUtil.<ResultModel<UserProfile>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<UserProfile>>dealError())
                        .doOnSuccess(new Consumer<ResultModel<UserProfile>>() {
                            @Override
                            public void accept(ResultModel<UserProfile> userProfile) {
                                userProfileLiveData.postValue(userProfile.getData());
                            }
                        })
        );
    }

    //拉取影圈列表
    public void fetchMyMomentsList() {
/*        bindLife(
                apiService.fetchMomentList()
                        .compose(RxUtil.<ResultModel<List<Moment>>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<List<Moment>>>dealError())
                        .compose(this.<ResultModel<List<Moment>>>autoProgressDialog())
                        .doOnSuccess(new Consumer<ResultModel<List<Moment>>>() {
                            @Override
                            public void accept(ResultModel<List<Moment>> userProfile) {
                                momentList.postValue(userProfile.getData());
                            }
                        })
        );*/
        List<Moment> moments = new ArrayList<>();
        moments.add(new Moment());
        moments.add(new Moment());
        moments.add(new Moment());
        moments.add(new Moment());
        momentListLiveData.postValue(moments);
    }

}
