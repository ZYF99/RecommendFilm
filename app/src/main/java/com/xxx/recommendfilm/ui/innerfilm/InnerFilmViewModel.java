package com.xxx.recommendfilm.ui.innerfilm;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class InnerFilmViewModel extends BaseViewModel {
    MutableLiveData<List<Film>> filmListLiveData = new MutableLiveData<>();
    private int startPage = 0;

    //刷新电影列表
    public void refreshFilmList(String classify) {
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
        List<Film> films = new ArrayList<>();
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        filmListLiveData.postValue(films);
    }

    //加载更多电影列表
    public void loadMoreFilmList() {
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
        List<Film> films = new ArrayList<>();
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        films.add(new Film());
        List<Film> newFilmList = new ArrayList<>();
        newFilmList.addAll(Objects.requireNonNull(filmListLiveData.getValue()));
        newFilmList.addAll(films);
        filmListLiveData.postValue(newFilmList);
    }
}
