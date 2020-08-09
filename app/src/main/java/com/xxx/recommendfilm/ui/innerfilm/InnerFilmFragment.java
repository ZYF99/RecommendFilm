package com.xxx.recommendfilm.ui.innerfilm;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentInnerFilmBinding;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.filmdetail.FilmDetailActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.xxx.recommendfilm.ui.filmdetail.FilmDetailActivity.KEY_MID;

public class InnerFilmFragment extends BaseFragment<FragmentInnerFilmBinding, InnerFilmViewModel> {

    public static final String KEY_CLASSIFY = "key_classify";
    InnerFilmRecyclerAdapter innerFilmRecyclerAdapter;
    public String classify = "";
    private Boolean isFirstInit = true;

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

        assert getArguments() != null;
        classify = getArguments().getString(KEY_CLASSIFY);

        viewModel.filmListLiveData.observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                innerFilmRecyclerAdapter.replaceData(films);
            }
        });

        viewModel.isRefreshing.observe(this, aBoolean -> binding.refreshLayout.setRefreshing(aBoolean));

        viewModel.isLoadingMore.observe(this, aBoolean -> ((InnerFilmRecyclerAdapter) Objects.requireNonNull(binding.rvFilm.getAdapter())).onLoadMore.postValue(aBoolean));

        //电影列表适配器
        innerFilmRecyclerAdapter = new InnerFilmRecyclerAdapter(this, R.layout.item_film, true, new ArrayList<>());
        binding.rvFilm.setAdapter(innerFilmRecyclerAdapter);

        //电影点击跳转详情
        innerFilmRecyclerAdapter.setOnCellClickListener(film -> {
            Intent intent = new Intent(getContext(), FilmDetailActivity.class);
            intent.putExtra(KEY_MID, film.getMid());
            startActivity(intent);
        });

        //下拉刷新监听
        binding.refreshLayout.setOnRefreshListener(() -> viewModel.refreshFilmList(classify));

        //上拉加载
        binding.rvFilm.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (viewModel.filmPageModelLiveData.getValue() != null) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (!viewModel.isLoadingMore.getValue()
                                && viewModel.filmPageModelLiveData.getValue().getPages() > 1
                                && !viewModel.filmPageModelLiveData.getValue().getIsLastPage()) {
                            viewModel.loadMoreFilmList(classify);
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void initData() {
        if (isFirstInit) {
            viewModel.refreshFilmList(classify);
            isFirstInit = false;
        }

    }
}
