package model;

import java.util.ArrayList;

public class ProjectCollection extends ArrayList<Graphghan> {

    // TODO: Add functionality of requires to effects here
    // REQUIRES: Name of given graphghan has not been added to list previously
    // MODIFIES: This
    // EFFECTS:  Adds a new graphghan to ProjectCollection with given name,
    //           rows, and columns. Default all colors white to start.
    public boolean addProject(String name, int rows, int columns) {

        Graphghan newProject;

        if (this.isEmpty()) {
            newProject = new Graphghan(name, rows, columns);
            this.add(newProject);
            return true;
        } else {
            for (Graphghan g: this) {
                if (g.getName().equals(name)) {
                    return false;
                } else {
                    newProject = new Graphghan(name, rows, columns);
                    this.add(newProject);
                    return true;
                }
            }
        }

        return false;

    }

    // REQUIRES: Given name has been previously assigned to a Graphghan object
    //           stored within the projects array list
    // MODIFIES: This
    // EFFECTS:  Removes the project from the array list. Does not affect other
    //           projects in the list
    public boolean removeProject(String name) {
        for (Graphghan g: this) {
            if (g.getName().equals(name)) {
                this.remove(g);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Graphghan> getProjects() {
        return this;
    }

}
