package com.xxx.recommendfilm.ui.innerfilm;

import androidx.lifecycle.LifecycleOwner;

import com.xxx.recommendfilm.databinding.ItemFilmBinding;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;

import java.util.List;

public class InnerFilmRecyclerAdapter extends BaseRecyclerAdapter<Film, ItemFilmBinding> {
    public InnerFilmRecyclerAdapter(
            LifecycleOwner lifecycleOwner,
            int layoutRes,
            Boolean hasLoadMore,
            List<Film> baseList
    ) {
        super(lifecycleOwner, layoutRes, hasLoadMore, baseList);
    }

    @Override
    public void bindData(ItemFilmBinding binding, int position) {

    }
}
