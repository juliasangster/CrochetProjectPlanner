package model;


import java.util.Calendar;
import java.util.Date;

// SOURCE: AlarmSystem
//         https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/commit/047c12f321ec713fae1f1a5dfdb01688ea1df596
// CLASS COMMENT: Class representing an event that occurs in application while running
public class Event {

    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    // EFFECTS: Constructs an event with the given description and the current time in system
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: Gets and returns date of an event
    public Date getDate() {
        return dateLogged;
    }

    // EFFECTS: Gets and returns description of an event
    public String getDescription() {
        return description;
    }

    // EFFECTS: Returns true if events are equal (same date and time logged, same description).
    //          Returns false otherwise
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    // EFFECTS: Returns the hashcode for the given event
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: Returns the string for the given event, which contains the date and description
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}

