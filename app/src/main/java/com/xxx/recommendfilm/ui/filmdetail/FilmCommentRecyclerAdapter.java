package com.xxx.recommendfilm.ui.filmdetail;

import androidx.lifecycle.LifecycleOwner;
import com.xxx.recommendfilm.databinding.ItemFilmCommentBinding;
import com.xxx.recommendfilm.model.film.FilmComment;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;
import java.util.List;

public class FilmCommentRecyclerAdapter extends BaseRecyclerAdapter<FilmComment, ItemFilmCommentBinding> {

    public FilmCommentRecyclerAdapter(
            LifecycleOwner lifecycleOwner,
            int layoutRes,
            Boolean hasLoadMore,
            List<FilmComment> baseList
    ) {
        super(lifecycleOwner, layoutRes, hasLoadMore, baseList);
    }

    @Override
    public void bindData(ItemFilmCommentBinding binding, int position) {
        binding.setFilmComment(baseList.get(position));
    }
}
