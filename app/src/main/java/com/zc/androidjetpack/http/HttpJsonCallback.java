package com.zc.androidjetpack.http;

import com.kunminx.architecture.data.convert.JsonCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

public class HttpJsonCallback<T> extends JsonCallback<T> {
    @Override
    public void onSuccess(Response<T> response) {
        super.onSuccess(response);
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public void downloadProgress(Progress progress) {
        super.downloadProgress(progress);
    }

    @Override
    public void uploadProgress(Progress progress) {
        super.uploadProgress(progress);
    }
}
