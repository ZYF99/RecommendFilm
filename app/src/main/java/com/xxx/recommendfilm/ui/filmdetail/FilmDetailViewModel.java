package com.xxx.recommendfilm.ui.filmdetail;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.film.FilmComment;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;


public class FilmDetailViewModel extends BaseViewModel {
    MutableLiveData<List<FilmComment>> filmCommentListLiveData = new MutableLiveData<>();

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
