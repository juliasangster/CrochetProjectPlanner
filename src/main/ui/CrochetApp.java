package ui;

// Code partially based on TellerApp package from package ca.ubc.cpsc210.bank.ui;

import model.Graphghan;
import model.GraphghanSquare;
import model.ProjectCollection;

import java.util.Scanner;

public class CrochetApp {
    private ProjectCollection projects;
    private Scanner input;

    // EFFECTS: runs the application
    public CrochetApp() {
        runCrochetApp();
    }

    // MODIFIES:
    // EFFECTS:
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

    // MODIFIES:
    // EFFECTS:
    public void processKeyInput(String keyInput) {
        if (keyInput.equals("n")) {
            newGraphghan();
        } else if (keyInput.equals("e")) {
            //editGraphghan();
        } else if (keyInput.equals("l")) {
            viewGraphghanList();
        } else {
            System.out.println("Your choice is not valid.");
            System.out.println("Please select again");
            startMenuOptions();
        }
    }

    // MODIFIES:
    // EFFECTS:
    // REQUIRES:
    private void setupCrochetApp() {
        projects = new ProjectCollection();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES:
    // EFFECTS:
    // REQUIRES:
    // EFFECTS: Displays a menu of start options to user through console
    public static void startMenuOptions() {
        System.out.println("\n\u001b[35m" + "Welcome to the crochet app!" + "\u001B[0m");
        System.out.println("\u001b[35m" + "Please select from the following options:" + "\u001B[0m");
        System.out.println("\tn -> create new project");
        System.out.println("\te -> edit a current project");
        System.out.println("\tl -> see a list of all projects");
        System.out.println("\tq -> quit");
    }

    // MODIFIES:
    // EFFECTS:
    // REQUIRES:
    public static void printColourBlock(GraphghanSquare graphghanSquare) {
        //        System.out.print("\u001b[20m" + "⬛" + "\u001B[0m");
    }

    // MODIFIES:
    // EFFECTS:
    // REQUIRES:
    // TODO: Remove requires
    // REQUIRES: String not in projects, string not empty
    private void newGraphghan() {
        System.out.print("What would you like to name your new project?\n");
        String name = input.next();

        System.out.print("How many rows?\n");
        int rows = input.nextInt();

        System.out.print("How many columns?\n");
        int columns = input.nextInt();

        projects.addProject(name,columns,rows);

        System.out.print("Your new graphghan " + name + " has been added to projects");

    }

    // MODIFIES:
    // EFFECTS:
    // REQUIRES:
    private void viewGraphghanList() {
        if (projects.isEmpty()) {
            System.out.println("There are no graphghans to show\n");
        } else {
            for (Graphghan g : projects) {
                System.out.println("Graphghan:" + g.getName());
                System.out.println("Dimensions " + g.getRows() + "X" + g.getColumns());
                System.out.println("========================");
            }
        }
        System.out.println("Returning you to the main menu");
    }

}
