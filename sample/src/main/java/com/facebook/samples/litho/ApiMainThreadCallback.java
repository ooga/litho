package com.facebook.samples.litho;

import android.os.Handler;

import com.facebook.litho.ComponentContext;

import org.kisio.NavitiaSDK.invokers.ApiCallback;
import org.kisio.NavitiaSDK.invokers.ApiException;

import java.util.List;
import java.util.Map;

public abstract class ApiMainThreadCallback<T> implements ApiCallback<T> {
    private Handler mainHandler;

    public ApiMainThreadCallback(ComponentContext c) {
        this.mainHandler = new Handler(c.getMainLooper());
    }

    public abstract void onMainThreadFailure(ApiException var1, int var2, Map<String, List<String>> var3);
    public abstract void onMainThreadSuccess(T var1, int var2, Map<String, List<String>> var3);

    @Override
    public void onFailure(final ApiException var1, final int var2, final Map<String, List<String>> var3) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                onMainThreadFailure(var1, var2, var3);
            }
        });
    }

    @Override
    public void onSuccess(final T var1, final int var2, final Map<String, List<String>> var3) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                onMainThreadSuccess(var1, var2, var3);
            }
        });
    }

    @Override
    public void onUploadProgress(long l, long l1, boolean b) {

    }

    @Override
    public void onDownloadProgress(long l, long l1, boolean b) {

    }
}
