package com.xxx.recommendfilm.ui.filmdetail;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.film.FilmComment;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class FilmDetailViewModel extends BaseViewModel {

    MutableLiveData<List<FilmComment>> filmCommentListLiveData = new MutableLiveData<>();
    public MutableLiveData<Film> filmLiveData = new MutableLiveData<>();

    //获取电影详细信息
    public void fetchFilmDetailInfo(Long mid){
                bindLife(
                apiService.fetchFilmDetailInfo(mid)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(autoProgressDialog())
                        .doOnSuccess(filmDetailModel -> filmLiveData.postValue(filmDetailModel.getData()))
        );
    }

    //获取影评列表
    public void fetchCommentList() {
/*        bindLife(
                apiService.fetchFilmListByClassify(classify)
                        .compose(RxUtil.<ResultModel<List<Film>>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<List<Film>>>dealError())
                        .compose(this.<ResultModel<List<Film>>>autoProgressDialog())
                        .doOnSuccess(new Consumer<ResultModel<List<Film>>>() {
                            @Override
                            public void accept(ResultModel<List<Film>> filmList) {
                                filmListLiveData.postValue(filmList.getData());
                            }
                        })
        );*/
        List<FilmComment> filmComments = new ArrayList<>();
        filmComments.add(new FilmComment());
        filmComments.add(new FilmComment());
        filmComments.add(new FilmComment());
        filmComments.add(new FilmComment());
        filmCommentListLiveData.postValue(filmComments);
        isShowLoadingProgress.postValue(false);
    }

}
