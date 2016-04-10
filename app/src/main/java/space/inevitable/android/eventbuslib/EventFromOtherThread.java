package space.inevitable.android.eventbuslib;


public class EventFromOtherThread {
    private final int count;

    public EventFromOtherThread( int count ){
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
