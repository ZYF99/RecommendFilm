package com.xxx.recommendfilm.ui.home;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityMainBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;
import com.xxx.recommendfilm.ui.film.FilmFragment;
import com.xxx.recommendfilm.ui.mine.MineFragment;
import com.xxx.recommendfilm.ui.moment.MomentFragment;
import com.xxx.recommendfilm.ui.notice.NoticeFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private Fragment currentFragment = new FilmFragment();

    @Override
    protected Class<MainViewModel> getViewModelClazz() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void setUpBottomNavigation() {
        //binding.bottomNavigation.
        //底部导航栏
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_film:
                    replaceFragment("film");
                    break;

                case R.id.action_notice:
                    replaceFragment("notice");
                    break;

                case R.id.action_moment:
                    replaceFragment("moment");
                    break;
                case R.id.action_mine:
                    replaceFragment("mine");
                    break;
            }
            return true;
        });
        binding.bottomNavigation.setSelectedItemId(R.id.action_film);
    }


    @Override
    protected void initView() {
        setUpBottomNavigation();
    }

    @Override
    protected void initData() {

    }

    private void replaceFragment(String tag) {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(tag);

        if (currentFragment == null) {
            switch (tag) {
                case "film":
                    currentFragment = new FilmFragment();
                    break;
                case "notice":
                    currentFragment = new NoticeFragment();
                    break;
                case "moment":
                    currentFragment = new MomentFragment();
                    break;
                case "mine":
                    currentFragment = new MineFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.maincontainer, currentFragment, tag).commit();
        } else getSupportFragmentManager().beginTransaction().show(currentFragment).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().getFragments().forEach(fragment -> {
            fragment.onActivityResult(requestCode, resultCode, data);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}