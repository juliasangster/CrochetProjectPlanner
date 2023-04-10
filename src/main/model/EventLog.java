package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// SOURCE: AlarmSystem
//         https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/commit/047c12f321ec713fae1f1a5dfdb01688ea1df596
// CLASS COMMENT: Singleton event log; represents a single record of events that occur in application while running

public class EventLog implements Iterable<Event> {
    // NOTE: Singleton design pattern
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: Constructs a new event log
    // NOTE:    private construtor as singleton design pattern
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS:  If event log has not yet been constructed, construct new event log
    //           Else, return the already constructed event log
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // MODIFIES: this
    // EFFECTS:  Adds given event e to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECTS:  Clears all events and adds log-clearing event to event log
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECTS: Returns an iterator over the events in event log
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
