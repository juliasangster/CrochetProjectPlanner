package model;

import java.util.ArrayList;

// CLASS COMMENT: Class representing a list of crochet projects. Extended from ArrayList<Graphghan> in order to
// inherit built-in ArrayList methods. See: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

public class ProjectCollection extends ArrayList<Graphghan> {

    // REQUIRES: rows >= 1 && columns >= 1
    // MODIFIES: this
    // EFFECTS:  IF: the list of projects contains a project with the given name
    //               - return false
    //           ELSE:
    //               - construct new graphghan with given name, rows, and columns
    //               - add this new graphghan to ProjectCollection
    //               - return true
    public boolean addProject(String name, int rows, int columns) {

        Graphghan newProject;

        if (this.getAllNames().contains(name)) {
            return false;
        }
        newProject = new Graphghan(name, rows, columns);
        this.add(newProject);
        return true;
    }

    // REQUIRES: no graphghans with duplicate names in this
    // MODIFIES: this
    // EFFECTS:  IF: the list is not empty and there is a graphghan with the same name in this
    //               - remove the graphghan with the same name from the list and return true
    //           ELSE: - return false
    public boolean removeProject(String name) {
        for (Graphghan g: this) {
            if (g.getName().equals(name)) {
                this.remove(g);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns a list that contains the name of all graphghans currently in this
    public ArrayList<String> getAllNames() {

        ArrayList<String> result = new ArrayList<>();

        for (Graphghan g : this) {
            String name = g.getName();
            result.add(name);
        }

        return result;
    }

    // EFFECTS: IF: there is a project in this with the given name, returns true.
    //          ELSE: returns false.
    public boolean containsGivenProject(String name) {
        ArrayList<String> allNames = this.getAllNames();
        boolean result = allNames.contains(name);
        return result;
    }

    // EFFECTS:  IF: the list is not empty and there is a graphghan with the same name in this
    //               - return the graphghan object with the same name as given name
    //           ELSE: - return a null graphghan object
    public Graphghan getGraphghanSpecificName(String name) {

        Graphghan desiredGraphghan = null;

        for (Graphghan g : this) {
            if (g.getName().equals(name)) {
                desiredGraphghan = g;
            }
        }

        return desiredGraphghan;
    }

}
