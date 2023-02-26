package model;

import java.util.ArrayList;

// CLASS COMMENT: Class representing a list of crochet projects

public class ProjectCollection extends ArrayList<Graphghan> {

    // TODO: FIX EFFECTS:
    // MODIFIES: this
    // EFFECTS:  if the list of projects is empty:
    //            - constructs and adds new graphghan to project collection
    //              with given name, rows, and columns
    //           else:
    //            - if the list already contains a graphghan with the given name
    //                - return false
    //            - else:
    //              - constructs and adds new graphghan to project collection with
    //                given name, rows, columns
    //              - return true
    //
    public boolean addProject(String name, int rows, int columns) {

        Graphghan newProject;

        if (this.getAllNames().contains(name)) {
            return false;
        }
        newProject = new Graphghan(name, rows, columns);
        this.add(newProject);
        return true;
    }

    // MODIFIES: this
    // EFFECTS:  if there is a project with the given name in the list:
    //              - removes project with given name from projects
    //              - returns true
    //           else:
    //              - returns false
    public boolean removeProject(String name) {
        for (Graphghan g: this) {
            if (g.getName().equals(name)) {
                this.remove(g);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns a list of String that contains all
    //          graphghan names. Does not add doubles if they exist.
    public ArrayList<String> getAllNames() {

        ArrayList<String> result = new ArrayList<>();

        for (Graphghan g : this) {
            String name = g.getName();
            result.add(name);
        }

        return result;
    }

    // EFFECTS: returns true if the given project with name
    //          is contained in the list of projects.
    //          returns false otherwise.
    public boolean containsGivenProject(String name) {
        ArrayList<String> allNames = this.getAllNames();
        boolean result = allNames.contains(name);
        return result;
    }

    // EFFECTS: IF the list is empty:
    //           - return null
    //          ELSE:
    //           - IF there is a graphghan with the given name
    //               - returns the graphghan object with given name
    //           - ELSE return null
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
