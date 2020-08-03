package com.xxx.recommendfilm.ui.film;

import android.util.Pair;
import androidx.lifecycle.Observer;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentFilmBinding;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.innerfilm.InnerFilmFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


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

        viewModel.classifyListLiveData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                strings.forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        fragmentList.add(Pair.create(new InnerFilmFragment(),s));
                    }
                });

                FilmPagerAdapter filmPagerAdapter = new FilmPagerAdapter(getChildFragmentManager(), fragmentList);
                binding.viewPager.setAdapter(filmPagerAdapter);
                binding.tabLayout.setupWithViewPager(binding.viewPager);
            }
        });
    }

    @Override
    protected void initData() {
        viewModel.fetchClassifyList();
    }
}