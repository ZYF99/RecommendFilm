package com.xxx.recommendfilm.ui.notice;

import androidx.lifecycle.LifecycleOwner;

import com.xxx.recommendfilm.databinding.ItemNoticeBinding;
import com.xxx.recommendfilm.model.notice.Notice;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;

import java.text.SimpleDateFormat;
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
        Notice notice = baseList.get(position);
        binding.setNotice(notice);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String str = simpleDateFormat.format(notice.getCreateTime());
        binding.tvTime.setText(str);
    }
}
