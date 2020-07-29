package com.xxx.recommendfilm.ui.moment;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.functions.Consumer;

public class MomentViewModel extends BaseViewModel {

    MutableLiveData<List<Moment>> momentList = new MutableLiveData<>();

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
        momentList.postValue(moments);
    }

}
