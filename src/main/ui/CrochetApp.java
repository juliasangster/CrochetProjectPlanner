package ui;

import model.Graphghan;
import model.ProjectCollection;

import java.util.Scanner;

public class CrochetApp {
    private ProjectCollection projects;
    private Scanner input;

    // EFFECTS: runs the application
    public CrochetApp() {

        runCrochetApp();
    }

    public void runCrochetApp() {
        boolean runApp = true;
        String keyInput = null;

        setupCrochetApp();

        while (runApp) {
            startMenuOptions();
            keyInput = input.next();
            keyInput = keyInput.toLowerCase();

            if (keyInput.equals("q")) {
                runApp = false;
            } else {
                processKeyInput(keyInput);
            }
        }




    }

    public void processKeyInput(String keyInput) {
        if (keyInput.equals("n")) {
            newGraphghan();
        } else if (keyInput.equals("e")) {
            editGraphghan();
        } else if (keyInput.equals("l")) {
            viewGraphghanList();
        } else {
            System.out.println("Your choice is not valid.");
            System.out.println("Please select again");
            startMenuOptions();
        }
    }

    private void newGraphghan() {
    }

    private void editGraphghan() {

    }

    private void viewGraphghanList () {
        for (Graphghan g : projects) {
            System.out.println("Project:" + g.getName() + "//" + g.getColumns() + "columns by" + g.getRows() + "rows");
        }
    }

    private void selectGraphghan() {

    }

    private void setupCrochetApp() {
        projects = new ProjectCollection();
        input = new Scanner(System.in);
        input.useDelimiter(" -> ");
    }

    // EFFECTS: Displays a menu of start options to user through console
    private void startMenuOptions() {
        System.out.println("\u001b[35m." + "Welcome to the crochet app!" + "\u001B[0m");
        System.out.println("\u001b[35m." + "Please select from the following options:" + "\u001B[0m");
        System.out.println("\tn -> create new project");
        System.out.println("\te -> edit a current project");
        System.out.println("\tl -> see a list of all projects");
        System.out.println("\tq -> quit");
    }



}
