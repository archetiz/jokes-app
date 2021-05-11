package kk.jokesapp.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public class UiExecutor implements Executor {
    private Handler handler;

    public UiExecutor() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
