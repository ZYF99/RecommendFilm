package com.xxx.recommendfilm.ui.filmdetail;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityFilmDetailBinding;
import com.xxx.recommendfilm.model.film.FilmComment;
import com.xxx.recommendfilm.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class FilmDetailActivity extends BaseActivity<ActivityFilmDetailBinding,FilmDetailViewModel> {

    FilmCommentRecyclerAdapter filmCommentRecyclerAdapter;
    List<FilmComment> filmCommentList = new ArrayList<>();

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
        //电影列表适配器
        filmCommentRecyclerAdapter = new FilmCommentRecyclerAdapter(this, R.layout.item_film_comment, true, filmCommentList);
        binding.rvComment.setAdapter(filmCommentRecyclerAdapter);
    }

    @Override
    protected void initData() {
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentList.add(new FilmComment());
        filmCommentRecyclerAdapter.notifyDataSetChanged();
    }
}