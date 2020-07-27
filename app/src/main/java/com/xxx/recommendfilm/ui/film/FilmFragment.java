package com.xxx.recommendfilm.ui.film;

import android.util.Pair;

import androidx.fragment.app.Fragment;

import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentFilmBinding;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.innerfilm.InnerFilmFragment;

import java.util.ArrayList;
import java.util.List;

public class FilmFragment extends BaseFragment<FragmentFilmBinding, FilmViewModel> {

    private List<Pair<Fragment, String>> fragmentList = new ArrayList<>();

    @Override
    protected Class<FilmViewModel> getViewModelClazz() {
        return FilmViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_film;
    }

    @Override
    protected void initView() {
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "喜剧"));
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "悲剧"));
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "动画片"));
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "情感片"));
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "科教片"));
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "纪录片"));
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "恐怖片"));
        fragmentList.add(Pair.<Fragment, String>create(new InnerFilmFragment(), "科幻片"));
        FilmPagerAdapter filmPagerAdapter = new FilmPagerAdapter(this.getChildFragmentManager(), fragmentList);
        binding.viewPager.setAdapter(filmPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void initData() {

    }
}