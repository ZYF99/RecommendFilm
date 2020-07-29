package com.xxx.recommendfilm.ui.film;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xxx.recommendfilm.ui.innerfilm.InnerFilmFragment;

import java.util.List;

public class FilmPagerAdapter extends FragmentPagerAdapter {

    private List<InnerFilmFragment> fragmentList;

    public FilmPagerAdapter(@NonNull FragmentManager fm, List<InnerFilmFragment>fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).classify;
    }
}