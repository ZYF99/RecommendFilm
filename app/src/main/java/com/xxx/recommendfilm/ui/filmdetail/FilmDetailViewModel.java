package com.xxx.recommendfilm.ui.filmdetail;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.film.FilmComment;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import java.util.List;
import java.util.Objects;

public class FilmDetailViewModel extends BaseViewModel {

    MutableLiveData<List<FilmComment>> filmCommentListLiveData = new MutableLiveData<>();
    public MutableLiveData<String> commentContentLiveData = new MutableLiveData<>("");
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

    //发起影评
    public void submitComment(Long mid) {
        if (Objects.requireNonNull(commentContentLiveData.getValue()).isEmpty()) return;
        bindLife(
                apiService.reviewFilm(new FilmComment(mid, commentContentLiveData.getValue()))
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(autoProgressDialog())
                        .doOnSuccess(s -> {
                            fetchFilmDetailInfo(mid);
                            commentContentLiveData.setValue("");
                        })
        );
    }

}
