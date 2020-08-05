package com.xxx.recommendfilm.ui.filmdetail;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityFilmDetailBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;
import java.util.ArrayList;

public class FilmDetailActivity extends BaseActivity<ActivityFilmDetailBinding, FilmDetailViewModel> {

    public static final String KEY_MID = "key_mid";

    FilmCommentRecyclerAdapter filmCommentRecyclerAdapter;

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
        //列表数据变化时，刷新RecyclerView列表的UI
        viewModel.filmCommentListLiveData.observe(this, filmComments -> filmCommentRecyclerAdapter.replaceData(filmComments));
        //电影列表适配器
        filmCommentRecyclerAdapter = new FilmCommentRecyclerAdapter(this, R.layout.item_film_comment, true, new ArrayList<>());
        binding.rvComment.setAdapter(filmCommentRecyclerAdapter);
    }

    @Override
    protected void initData() {
        //得到传进来的mid
        Long mid = getIntent().getLongExtra(KEY_MID, 0);
        //根据传进来的mid获取电影详情信息
        viewModel.fetchFilmDetailInfo(mid);
    }
}