package com.xxx.recommendfilm.ui.film;

import android.os.Bundle;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xxx.recommendfilm.ui.innerfilm.InnerFilmFragment;

import java.util.List;

import static com.xxx.recommendfilm.ui.innerfilm.InnerFilmFragment.KEY_CLASSIFY;

public class FilmPagerAdapter extends FragmentPagerAdapter {

    private List<Pair<InnerFilmFragment, String>> fragmentList;

    public FilmPagerAdapter(@NonNull FragmentManager fm, List<Pair<InnerFilmFragment, String>> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CLASSIFY,fragmentList.get(position).second);
        fragmentList.get(position).first.setArguments(bundle);
        return fragmentList.get(position).first;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).second;
    }
}