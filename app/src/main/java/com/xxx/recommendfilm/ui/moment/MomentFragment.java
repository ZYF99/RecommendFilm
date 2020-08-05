package com.xxx.recommendfilm.ui.moment;

import android.content.Intent;
import androidx.annotation.Nullable;
import com.luck.picture.lib.PictureSelector;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.FragmentMomentBinding;
import com.xxx.recommendfilm.model.moment.Moment;
import com.xxx.recommendfilm.ui.base.BaseFragment;
import com.xxx.recommendfilm.ui.release.ReleaseActivity;
import com.xxx.recommendfilm.ui.util.PictureSelectUtil;
import java.util.ArrayList;
import static android.app.Activity.RESULT_OK;
import static com.xxx.recommendfilm.ui.release.ReleaseActivity.KEY_IMG;
import static com.xxx.recommendfilm.ui.util.PictureSelectUtil.REQUESTCODE_AVATAR;

public class MomentFragment extends BaseFragment<FragmentMomentBinding, MomentViewModel> {

    MomentRecyclerAdapter momentRecyclerAdapter;

    @Override
    protected Class<MomentViewModel> getViewModelClazz() {
        return MomentViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_moment;
    }

    @Override
    protected void initView() {

        viewModel.momentList.observe(this, moments -> momentRecyclerAdapter.replaceData(moments));

        //电影列表适配器
        momentRecyclerAdapter = new MomentRecyclerAdapter(this, R.layout.item_moment, true, new ArrayList<Moment>());
        binding.rvMoment.setAdapter(momentRecyclerAdapter);

        //电影点击跳转详情
        momentRecyclerAdapter.setOnCellClickListener(Moment -> {

        });

        //下拉刷新监听
        binding.refreshLayout.setOnRefreshListener(() -> {

        });

        //发布影圈
        binding.fbAdd.setOnClickListener(v -> {
            PictureSelectUtil.showAvatarAlbum(getActivity());
        });
    }

    @Override
    protected void initData() {
        viewModel.fetchMyMomentsList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_AVATAR) {
                String imgPath = PictureSelector.obtainMultipleResult(data).get(0).getPath();
                Intent intent = new Intent(getContext(), ReleaseActivity.class);
                intent.putExtra(KEY_IMG, imgPath);
                startActivity(intent);
            }
        }
    }
}