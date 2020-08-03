package com.xxx.recommendfilm.ui.film;

import androidx.lifecycle.MutableLiveData;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.film.ClassifyListModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;
import java.util.List;
import io.reactivex.functions.Consumer;

public class FilmViewModel extends BaseViewModel {

    MutableLiveData<List<String>> classifyListLiveData = new MutableLiveData();

    //拉取电影分类列表
    public void fetchClassifyList() {
        bindLife(
                apiService.fetchFilmClassifyList()
                        .compose(RxUtil.<ResultModel<ClassifyListModel>>switchThread())
                        .compose(ApiErrorUtil.<ResultModel<ClassifyListModel>>dealError())
                        .doOnSuccess(new Consumer<ResultModel<ClassifyListModel>>() {
                            @Override
                            public void accept(ResultModel<ClassifyListModel> resultModel) {
                                classifyListLiveData.postValue(resultModel.getData().getClassifyList());
                            }
                        })
        );
    }

}
