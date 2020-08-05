package com.xxx.recommendfilm.ui.filmdetail;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.film.FilmComment;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import java.util.List;

public class FilmDetailViewModel extends BaseViewModel {

    MutableLiveData<List<FilmComment>> filmCommentListLiveData = new MutableLiveData<>();
    public MutableLiveData<Film> filmLiveData = new MutableLiveData<>();

    //获取电影详细信息
    public void fetchFilmDetailInfo(Long mid) {
        bindLife(
                apiService.fetchFilmDetailInfo(mid)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(autoProgressDialog())
                        .doOnSuccess(filmDetailModel -> {
                                    //电影详情model赋值
                                    filmLiveData.postValue(filmDetailModel.getData());
                                    //影评列表赋值
                                    filmCommentListLiveData.postValue(filmDetailModel.getData().getMovieReviewDetailRspList());
                                }
                        )
        );
    }

}
