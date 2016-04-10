package space.inevitable.android.thread;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public final class UiThreadExecutor implements Executor {
    private Handler handler;

    @Override
    public void execute( @NonNull final Runnable command ) {
        makeSureHandlerIsNotNull();
        handler.post( command );
    }

    private void makeSureHandlerIsNotNull() {
        if ( handler != null ) {
            return;
        }

        final Looper mainLooper = Looper.getMainLooper();
        handler = new Handler( mainLooper );
    }
}
