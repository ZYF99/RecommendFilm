package com.xxx.recommendfilm.ui.film;

import android.content.Intent;
import android.util.Pair;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentFilmBinding;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.innerfilm.InnerFilmFragment;
import com.xxx.recommendfilm.ui.search.SearchActivity;
import java.util.ArrayList;
import java.util.List;

public class FilmFragment extends BaseFragment<FragmentFilmBinding, FilmViewModel> {

    private List<Pair<InnerFilmFragment,String>> fragmentList = new ArrayList<>();

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

        //监听分类列表，更新tab
        viewModel.classifyListLiveData.observe(this, strings -> {
            strings.forEach(s -> fragmentList.add(Pair.create(new InnerFilmFragment(),s)));

            FilmPagerAdapter filmPagerAdapter = new FilmPagerAdapter(getChildFragmentManager(), fragmentList);
            binding.viewPager.setAdapter(filmPagerAdapter);
            binding.viewPager.setOffscreenPageLimit(strings.size()-1);
            binding.tabLayout.setupWithViewPager(binding.viewPager);
        });

        //搜索框点击事件
        binding.tvSearch.setOnClickListener(v -> {
            //跳转搜索界面
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        viewModel.fetchClassifyList();
    }
}