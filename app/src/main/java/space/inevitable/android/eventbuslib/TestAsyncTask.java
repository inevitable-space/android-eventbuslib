package space.inevitable.android.eventbuslib;

import android.os.AsyncTask;

import space.inevitable.eventbus.EventBus;


class TestAsyncTask extends AsyncTask< Void, Void, Void > {
    private EventBus eventBus;

    public TestAsyncTask( EventBus eventBus ) {
        this.eventBus = eventBus;
    }

    @Override
    protected Void doInBackground( Void... params ) {
        try {

            for ( int i = 0; i <= 5; i++ ) {
                Thread.sleep( 1000 );
                EventFromOtherThread eventFromOtherThread = new EventFromOtherThread( i );
                eventBus.post( eventFromOtherThread );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return null;
    }
}
