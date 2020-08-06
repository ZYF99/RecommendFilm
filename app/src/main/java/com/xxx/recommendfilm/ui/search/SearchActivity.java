package com.xxx.recommendfilm.ui.search;

import android.content.Intent;
import android.view.inputmethod.EditorInfo;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivitySearchBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;
import com.xxx.recommendfilm.ui.filmdetail.FilmDetailActivity;
import com.xxx.recommendfilm.ui.innerfilm.InnerFilmRecyclerAdapter;
import java.util.ArrayList;
import java.util.Objects;

import static com.xxx.recommendfilm.ui.filmdetail.FilmDetailActivity.KEY_MID;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    InnerFilmRecyclerAdapter innerFilmRecyclerAdapter;

    @Override
    protected Class<SearchViewModel> getViewModelClazz() {
        return SearchViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }


    @Override
    protected void initView() {

        binding.etSearch.requestFocus();

        viewModel.isRefreshing.observe(this, aBoolean -> binding.refreshLayout.setRefreshing(aBoolean));

        viewModel.isLoadingMore.observe(this, aBoolean -> ((InnerFilmRecyclerAdapter) Objects.requireNonNull(binding.rvFilm.getAdapter())).onLoadMore.postValue(aBoolean));

        viewModel.filmPageModelLiveData.observe(this, filmPageModel -> {
            innerFilmRecyclerAdapter.replaceData(filmPageModel.getDataList());
            binding.refreshLayout.setRefreshing(false);
        });

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        //电影列表适配器
        innerFilmRecyclerAdapter = new InnerFilmRecyclerAdapter(this, R.layout.item_film, true, new ArrayList<>());
        binding.rvFilm.setAdapter(innerFilmRecyclerAdapter);

        //电影点击跳转详情
        innerFilmRecyclerAdapter.setOnCellClickListener(film -> {
            Intent intent = new Intent(SearchActivity.this, FilmDetailActivity.class);
            intent.putExtra(KEY_MID, film.getMid());
            startActivity(intent);
        });

        //键盘搜素按钮监听
        binding.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.refreshFilmList();
                return true;
            }
            return false;
        });

        //下拉刷新监听
        binding.refreshLayout.setOnRefreshListener(() -> viewModel.refreshFilmList());

        //上拉加载
        binding.rvFilm.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (viewModel.filmPageModelLiveData.getValue() != null) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (!viewModel.isLoadingMore.getValue() && viewModel.filmPageModelLiveData.getValue().getPages() > 1) {
                            viewModel.loadMoreFilmList();
                        }
                    }
                }


            }
        });
    }

    @Override
    protected void initData() {

    }
}