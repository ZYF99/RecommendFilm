package com.xxx.recommendfilm.ui.moment;

import androidx.lifecycle.LifecycleOwner;

import com.xxx.recommendfilm.databinding.ItemFilmBinding;
import com.xxx.recommendfilm.databinding.ItemMomentBinding;
import com.xxx.recommendfilm.model.film.Film;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;

import java.util.List;

public class MomentRecyclerAdapter extends BaseRecyclerAdapter<Moment, ItemMomentBinding> {

    public MomentRecyclerAdapter(
            LifecycleOwner lifecycleOwner,
            int layoutRes,
            Boolean hasLoadMore,
            List<Moment> baseList
    ) {
        super(lifecycleOwner, layoutRes, hasLoadMore, baseList);
    }

    @Override
    public void bindData(ItemMomentBinding binding, int position) {
        binding.setMoment(baseList.get(position));
    }
}
