package space.inevitable.android.eventbuslib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import space.inevitable.android.eventbus.invoke.UiThreadInvoker;
import space.inevitable.eventbus.EventBus;
import space.inevitable.eventbus.StandardEventBusBuilder;
import space.inevitable.eventbus.Subscribe;

public class ShowCaseActivity extends AppCompatActivity {
    TestAsyncTask testAsyncTask;
    EventBus eventBus;
    TextView textView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_case );

        prepareEventBus();
        testAsyncTask = new TestAsyncTask( eventBus );
        textView = ( TextView ) findViewById( R.id.textView );
        testAsyncTask.execute();
    }

    private void prepareEventBus(){
        StandardEventBusBuilder standardEventBusBuilder = new StandardEventBusBuilder();
        eventBus = standardEventBusBuilder.build();
        eventBus.addInvoker( new UiThreadInvoker( eventBus ) );
        eventBus.register( this );
    }

    @Subscribe( "UiThreadInvoker" )
    public void onEventFromOtherThread( final EventFromOtherThread eventFromOtherThread ) {
        int count = eventFromOtherThread.getCount();
        textView.setText( "count: " + count );
    }
}
