package com.xxx.recommendfilm.ui.innerfilm;

import android.content.Intent;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentInnerFilmBinding;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;
import com.xxx.recommendfilm.ui.filmdetail.FilmDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class InnerFilmFragment extends BaseFragment<FragmentInnerFilmBinding, InnerFilmViewModel> {

    InnerFilmRecyclerAdapter innerFilmRecyclerAdapter;
    List<Film> filmList = new ArrayList<>();

    @Override
    protected Class<InnerFilmViewModel> getViewModelClazz() {
        return InnerFilmViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_inner_film;
    }

    @Override
    protected void initView() {

        //电影列表适配器
        innerFilmRecyclerAdapter = new InnerFilmRecyclerAdapter(this, R.layout.item_film, true, filmList);
        binding.rvFilm.setAdapter(innerFilmRecyclerAdapter);

        //电影点击跳转详情
        innerFilmRecyclerAdapter.setOnCellClickListener(new BaseRecyclerAdapter.OnCellClickListener<Film>() {
            @Override
            public void onCellClick(Film film) {
                startActivity(new Intent(getContext(), FilmDetailActivity.class));
            }
        });

        //下拉刷新监听
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    protected void initData() {
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        filmList.add(new Film());
        innerFilmRecyclerAdapter.notifyDataSetChanged();
    }
}
