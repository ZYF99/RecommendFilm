package com.xxx.recommendfilm.ui.notice;

import androidx.lifecycle.LifecycleOwner;

import com.xxx.recommendfilm.databinding.ItemMomentBinding;
import com.xxx.recommendfilm.databinding.ItemNoticeBinding;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.model.notice.Notice;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;

import java.util.List;

public class NoticeRecyclerAdapter extends BaseRecyclerAdapter<Notice, ItemNoticeBinding> {

    public NoticeRecyclerAdapter(
            LifecycleOwner lifecycleOwner,
            int layoutRes,
            Boolean hasLoadMore,
            List<Notice> baseList
    ) {
        super(lifecycleOwner, layoutRes, hasLoadMore, baseList);
    }

    @Override
    public void bindData(ItemNoticeBinding binding, int position) {
        binding.setNotice(baseList.get(position));
    }
}
