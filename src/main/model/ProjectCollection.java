package model;

import java.util.ArrayList;
import model.Graphghan;

public class ProjectCollection extends ArrayList<Graphghan> {

    // MODIFIES: This
    // EFFECTS:  Adds a new graphghan to ProjectCollection with given name,
    //           rows, and columns. Default all colors white to start.
    public void addProject(String name, int rows, int columns) {
        Graphghan newProject = new Graphghan(name, rows, columns);
        this.add(newProject);
    }

    // REQUIRES: Given name has been previously assigned to a Graphghan object
    //           stored within the projects array list
    // MODIFIES: This
    // EFFECTS:  Removes the project from the array list. Does not affect other
    //           projects in the list
    public void removeProject(String name) {
        for (Graphghan g: this) {
            if (g.getName().equals(name)) {
                this.remove(g);
            }
        }
    }

    public ArrayList<Graphghan> getProjects() {
        return this;
    }
}
