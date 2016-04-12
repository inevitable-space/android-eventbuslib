package space.inevitable.android.eventbus.invoke;

import java.lang.reflect.Method;

import space.inevitable.android.thread.UiThreadExecutor;
import space.inevitable.eventbus.EventBus;
import space.inevitable.eventbus.beans.ExecutionBundle;
import space.inevitable.eventbus.invoke.Invoker;
import space.inevitable.eventbus.invoke.InvokerRunnable;

public final class UiThreadInvoker extends Invoker {
    private final EventBus eventBus;
    private final UiThreadExecutor uiThreadExecutor = new UiThreadExecutor();

    public UiThreadInvoker( EventBus eventBus ) {
        this.eventBus = eventBus;
    }

    @Override
    public void invoke( final ExecutionBundle executionBundle, final Object eventInstance ) {
        final Method method = executionBundle.getMethod();
        final Object listener = executionBundle.getListener();

        final Runnable runnable = new InvokerRunnable( method, listener, eventInstance, eventBus );
        uiThreadExecutor.execute( runnable );
    }

    @Override
    public String getName() {
        return UiThreadInvoker.class.getSimpleName();
    }

    @Override
    public int getExecutionPriority() {
        return 0;
    }
}
