package com.xxx.recommendfilm.ui.release;

import androidx.lifecycle.MutableLiveData;

import com.xxx.recommendfilm.BuildConfig;
import com.xxx.recommendfilm.model.ResultModel;
import com.xxx.recommendfilm.model.UploadImageResultModel;
import com.xxx.recommendfilm.model.moment.ReleaseMomentRequestModel;
import com.xxx.recommendfilm.ui.base.BaseViewModel;
import com.xxx.recommendfilm.util.ApiErrorUtil;
import com.xxx.recommendfilm.util.RxUtil;

import java.io.File;

import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ReleaseViewModel extends BaseViewModel {
    public MutableLiveData<String> inputContentData = new MutableLiveData();
    public MutableLiveData<String> imgUrlLiveData = new MutableLiveData();

    public void releaseMoment(Action action) {
        File file = new File(imgUrlLiveData.getValue());
        RequestBody photoRequestBody =
                RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData(
                "imageFile",
                file.getName(),
                photoRequestBody
        );

        bindLife(
                apiService.upLoadImage(photo)
                        .flatMap((Function<ResultModel<UploadImageResultModel>, SingleSource<?>>) result ->
                                apiService.releaseMoment(new ReleaseMomentRequestModel(
                                        inputContentData.getValue(),
                                        BuildConfig.BASE_URL + "/image/" + result.getData().getImagePath()
                                ))).compose(RxUtil.switchThread())
                        .compose(ApiErrorUtil.dealError())
                        .doOnSuccess(o -> action.run())
        );
    }
}
