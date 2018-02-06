package com.example.kotlinxc.tools;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by zf on 2018/2/5.
 */

public abstract class ExecutorTool<T> implements OnResponseListener<T> {

    private Executor mExecutor;

    public ExecutorTool() {

        final Handler mHandler = new Handler(Looper.getMainLooper());

        mExecutor = new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                mHandler.post(command);
            }
        };
    }

    @Override
    public void response(final T data) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {

                onResponseListener(data);
            }
        });
    }

    public abstract void onResponseListener(T t);

}
