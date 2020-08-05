package com.xxx.recommendfilm.ui.search;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.film.FilmPageModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends BaseViewModel {

    public MutableLiveData<String> searchKey = new MutableLiveData<>("");
    MutableLiveData<FilmPageModel> filmPageModelLiveData = new MutableLiveData<>();
    MutableLiveData<List<Film>> filmListLiveData = new MutableLiveData<List<Film>>(new ArrayList<Film>());
    private int currentPageNum = 1;
    MutableLiveData<Boolean> isLoadingMore = new MutableLiveData<>(false);
    MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>(false);

    //刷新电影列表
    public void refreshFilmList() {
        bindLife(
                apiService.fetchFilmListByName(searchKey.getValue(), 1, 10)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSubscribe(disposable -> isRefreshing.postValue(true))
                        .doOnSuccess(filmList -> {
                            currentPageNum = 1;
                            filmPageModelLiveData.postValue(filmList.getData());
                            filmListLiveData.postValue(filmPageModelLiveData.getValue().getDataList());
                        })
                        .doFinally(() -> isRefreshing.postValue(false))
        );
    }

    //加载更多电影列表
    public void loadMoreFilmList() {
        final int targetPageNum = currentPageNum + 1;
        bindLife(
                apiService.fetchFilmListByClassify(searchKey.getValue(), targetPageNum, 10)
                        .compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .compose(this.autoProgressDialog())
                        .doOnSubscribe(disposable -> isLoadingMore.postValue(true))
                        .doOnSuccess(filmPageModel -> {
                            filmPageModelLiveData.postValue(filmPageModel.getData());
                            List<Film> newList = filmListLiveData.getValue();
                            assert newList != null;
                            newList.addAll(filmPageModel.getData().getDataList());
                            filmListLiveData.postValue(newList);
                            currentPageNum = filmPageModel.getData().getPageNum();
                        })
                        .doFinally(() -> isLoadingMore.postValue(false))
        );
    }
}
