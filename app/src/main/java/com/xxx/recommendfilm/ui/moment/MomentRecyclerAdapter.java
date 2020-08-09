package com.xxx.recommendfilm.ui.moment;

import androidx.lifecycle.LifecycleOwner;
import com.xxx.recommendfilm.databinding.ItemMomentBinding;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseRecyclerAdapter;
import java.text.SimpleDateFormat;
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
        Moment moment = baseList.get(position);
        binding.setMoment(moment);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String str = simpleDateFormat.format(moment.getCreateTime());
        binding.tvTime.setText(str);
    }
}
