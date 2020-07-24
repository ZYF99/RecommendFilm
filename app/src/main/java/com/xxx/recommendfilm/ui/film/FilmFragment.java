package com.xxx.recommendfilm.ui.film;

import androidx.fragment.app.Fragment;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentFilmBinding;
import com.xxx.recommendfilm.ui.base.BaseFragment;

public class FilmFragment extends BaseFragment<FragmentFilmBinding,FilmViewModel> {

    @Override
    protected Class<FilmViewModel> getViewModelClazz() { return FilmViewModel.class; }

    @Override
    protected int getLayoutRes() { return R.layout.fragment_film; }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}