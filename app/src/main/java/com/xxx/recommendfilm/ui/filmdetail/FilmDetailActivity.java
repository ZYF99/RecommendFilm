package com.xxx.recommendfilm.ui.filmdetail;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityFilmDetailBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;

public class FilmDetailActivity extends BaseActivity<ActivityFilmDetailBinding,FilmDetailViewModel> {

    @Override
    protected Class<FilmDetailViewModel> getViewModelClazz() {
        return FilmDetailViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_film_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}