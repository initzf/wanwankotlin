package com.example.kotlinxc.tools;

import java.util.concurrent.Executor;

/**
 * Created by zf on 2018/2/5.
 */

public abstract class ExecutorTool<T> implements OnResponseListener<T> {

    private Executor mExecutor;

    public ExecutorTool() {
        mExecutor = AppExecutors.getInstance().getMainThread();
    }

    @Override
    public void asyncResponse(final T data) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                onSyncResponse(data);
            }
        });
    }

    public abstract void onSyncResponse(T t);

}
