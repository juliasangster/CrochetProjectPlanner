package ui;

// SOURCES:
//   - TellerApplication: https://github.students.cs.ubc.ca/CPSC210/TellerApp

import model.Color;
import model.Graphghan;
import model.GraphghanSquare;
import model.ProjectCollection;

import java.util.Scanner;

// Crochet Application Class: represents a state of a crochet application
// with a ProjectCollection and inputs via Scanner

public class CrochetApp {
    private ProjectCollection projects;
    private Scanner input;

    private static final int MAX_CHOICE_LOOPS = 5;

    // EFFECTS: runs the application
    public CrochetApp() {
        runCrochetApp();
    }

    // MODIFIES: this
    // EFFECTS:  Processes the user inputs from the main menu of
    //           the crochet app
    public void runCrochetApp() {
        boolean runApp = true;
        String keyInput = null;

        setupCrochetApp();
        welcomeMessage();

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

    // MODIFIES: this
    // EFFECTS:  processes user inputs from the main manu
    public void processKeyInput(String keyInput) {
        if (keyInput.equals("n")) {
            newGraphghan();
        } else if (keyInput.equals("e")) {
            editGraphghan();
        } else if (keyInput.equals("l")) {
            viewGraphghanList();
        } else if (keyInput.equals("d")) {
            deleteGraphghan();
        } else {
            System.out.println("Your choice is not valid.");
            System.out.println("Please select again");
            startMenuOptions();
        }
    }

    // MODIFIES: this
    // EFFECTS:  initializes an empty Project Collection (list of projects),
    //           and the Scanner object needed to run the application
    private void setupCrochetApp() {
        projects = new ProjectCollection();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: Displays a welcome message
    public static void welcomeMessage() {
        System.out.println("\n\u001b[35m" + "Welcome to the crochet app!" + "\u001B[0m");
        System.out.println("\u001b[35m" + "Please select from the following options:" + "\u001B[0m");
    }

    // EFFECTS: Displays a menu of main menu options for user
    public static void startMenuOptions() {
        System.out.println("\n\tn -> create new project");
        System.out.println("\td -> delete an existing project");
        System.out.println("\te -> edit a current project");
        System.out.println("\tl -> see a list of all projects");
        System.out.println("\tq -> quit");
    }


    // EFFECTS:  Prints a single block character representing a Graphghan
    //           square to the console, in the associated color
    public static void printColourBlock(GraphghanSquare graphghanSquare) {
        System.out.print(graphghanSquare.getColor().getColorCode() + "⬛ " + "\u001B[0m");
    }

    // EFFECTS: Prints the given row of Graphghan Squares in the
    //          associated color for each square
    public static void printRow(GraphghanSquare[] squareRow) {
        for (GraphghanSquare square: squareRow) {
            printColourBlock(square);
        }
        System.out.println(" ");
    }

    // MODIFIES: this
    // REQUIRES:
    // EFFECTS:  processes a deletion of a graphghan. Prints success
    //           message if successful, and failure message if
    //           graphghan fails to be removed
    private void deleteGraphghan() {
        Graphghan selected = selectGraphghan();
        String nameSelected = selected.getName();
        boolean result = projects.removeProject(nameSelected);
        assert !(projects.containsGivenProject(nameSelected));
        if (result) {
            System.out.println("Successfully removed" + nameSelected + "\n");
        } else {
            System.out.println("Could not remove" + nameSelected + "\n");
            System.out.println("Please double-check list of projects\n");
        }

    }

    // REQUIRES: graphghan to print is not null
    // EFFECTS:  prints a coloured-block representation of the
    //           graphghan to the console
    public static void printGraphghan(Graphghan graphghan) {

        for (GraphghanSquare[] squareRow : graphghan.getSquares()) {
            printRow(squareRow);
        }
    }

    // MODIFIES: this
    // EFFECTS:  processes the addition of a new project to the
    //           project list.
    //           handles exceptional cases (row/column < 1, and empty
    //           name given) by printing error message to users
    //           and not adding graphghan
    private void newGraphghan() {
        try {
            System.out.print("What would you like to name your new project?\n");
            String name = input.next();

            System.out.print("How many rows?\n");
            int rows = input.nextInt();
            System.out.print("How many columns?\n");
            int columns = input.nextInt();

            if (rows < 1 || columns < 1 || name.equals("")) {
                throw new Exception();
            }

            projects.addProject(name, columns, rows);

            System.out.print("Your new graphghan " + name + " has been added to projects");

        } catch (Exception e) {
            this.input = new Scanner(System.in);
            System.out.print("Failed to add graphghan, please try again");
        }


    }

    // EFFECTS: IF the project list is empty:
    //              - returns an error message to user
    //          ELSE: prints information (name, dimensions) of all
    //          graphghans currently stored in projects
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

    // EFFECTS: prompts the user to select a graphghan in the list
    //          and returns it.
    //          prints success message and graphghan once selected
    private Graphghan selectGraphghan() {
        String selection = ""; // forces entry into loop
        boolean runLoop = false;

        while (!runLoop) {
            System.out.println("Please write the exact name of the project.");
            System.out.println("The names of your active projects are below:");
            for (Graphghan g : projects) {
                System.out.println("- " + g.getName());
            }
            selection = input.next();
            runLoop = projects.containsGivenProject(selection);
        }

        System.out.println("You have selected" + selection);

        return projects.getGraphghanSpecificName(selection);

    }

    // EFFECTS: IF list of projects is empty:
    //              - returns a message that editing cannot be completed
    //          ELSE:
    //              - prompts the user to select a graphghan to edit and
    //                then processes editing
    private void editGraphghan() {
        if (projects.isEmpty()) {
            System.out.println("There are no graphghans to select. Please add a new project.");
        } else {
            Graphghan selectedGraphghan = selectGraphghan();
            printGraphghan(selectedGraphghan);
            editGraphghanAllOptions(selectedGraphghan);
        }
    }

    // EFFECTS: processes editing inputs and outputs to the console
    //          while editing is occurring
    private void editGraphghanAllOptions(Graphghan selectedGraphghan) {
        editingOptions();

        boolean editing = true;

        String editCommand = input.next();

        while (editing) {
            selectEditingTool(editCommand,
                        selectedGraphghan);
            boolean choice;
            try {
                System.out.println("Continue editing?");
                choice = continueOrStop();
            } catch (Exception e) {
                choice = false;
            }
            if (choice) {
                editingOptions();
                editCommand = input.next();
            }
            editing = choice;
        }
        System.out.println("You have selected to finish editing. Returning to main menu.");
    }

    // EFFECTS: prints list of editing tool options
    private void editingOptions() {
        System.out.println("\t sqr -> Change colour of 1 square");
        System.out.println("\t row -> Change colour of 1 row");
        System.out.println("\t col -> Change colour of 1 column");
        System.out.println("\t fil -> Change colour entire graphghan");
        System.out.println("\t opt -> See colour options");
        System.out.println("\t q   -> Quit editing");
    }

    // EFFECTS: processes user inputs when in editing menu, including
    //          reuse tool input
    private void selectEditingTool(String editCommand, Graphghan selectedGraphghan) {

        boolean runLoop = true;
        try {
            while (runLoop) {
                if (editCommand.matches("sqr|row|col|fil|opt")) {
                    editCommand = editingToolsThatRepeat(editCommand, selectedGraphghan);
                } else if (editCommand.equals("opt")) {
                    seeColourOptions();
                    System.out.println("See again?");
                    runLoop = continueOrStop();
                } else if (editCommand.equals("q")) {
                    runLoop = false;
                    continue;
                } else {
                    System.out.println("Selection was not valid. Try again?");
                    runLoop = continueOrStop();
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    private String editingToolsThatRepeat(String editCommand, Graphghan selectedGraphghan) {

        boolean runLoop = true;
        try {
            while (runLoop) {
                if (editCommand.equals("sqr")) {
                    changeSquare(selectedGraphghan);
                } else if (editCommand.equals("row")) {
                    changeRow(selectedGraphghan);
                } else if (editCommand.equals("col")) {
                    changeColumn(selectedGraphghan);
                } else if (editCommand.equals("fil")) {
                    fillGraphghan(selectedGraphghan);
                } else if (editCommand.equals("opt")) {
                    seeColourOptions();
                }
                System.out.println("Use same tool?");
                runLoop = continueOrStop();
            }
        } catch (Exception e) {
            runLoop = false;
        }
        return "q";
    }

    // REQUIRES: selectedGraphghan is not null
    // MODIFIES: this
    // EFFECTS:  prompts user to select color and row of the
    //           selectedGraphghan.
    //           tries
    private void changeSquare(Graphghan selectedGraphghan) {

        try {
            Color color = selectColor();
            int row = getRowInput();
            int column = getColumnInput();
            if (row < 0 || column < 0) {
                throw new Exception();
            }
            selectedGraphghan.changeColorSingleSquare(color, row, column);
        } catch (Exception e) {
            System.out.println("Error changing, please try again");
        }
        printGraphghan(selectedGraphghan);
    }

    // TODO: MIGHT WANT EXCEPTION HANDLING HERE
    private boolean continueOrStop() {

        for (int loopCount = 0; loopCount < MAX_CHOICE_LOOPS; loopCount++) {
            System.out.print("Y/N?");
            String choice = input.next();
            choice = choice.toUpperCase();
            if (choice.equals("Y")) {
                System.out.println("Selected Y");
                return true;
            } else if (choice.equals("N")) {
                System.out.println("Selected N");
                return false;
            }
        }
        System.out.println("Selection failed, returning");
        return false;
    }

    private Color selectColor() throws Exception {

        String colorChoice = ""; // forces entry into loop
        boolean runLoop = true;
        Color output = null;
        int loopCounter = 0;

        while (runLoop) {
            System.out.println("What colour would you like?");
            colorChoice = input.next();
            colorChoice = colorChoice.toUpperCase();
            for (Color color : Color.values()) {
                if (colorChoice.equals(color.name())) {
                    output = color;
                    runLoop = false;
                }
            }
            loopCounter++;

            if (loopCounter >= MAX_CHOICE_LOOPS) {
                throw new Exception();
            }
        }
        return output;
    }

    private void changeRow(Graphghan selectedGraphghan) {
        try {
            Color color = selectColor();
            int row = getRowInput();
            if (row < 0) {
                throw new Exception();
            }
            selectedGraphghan.changeColorEntireRow(color, row);
        } catch (Exception e) {
            System.out.println("Error changing, please try again");
        }
        printGraphghan(selectedGraphghan);
    }

    private void changeColumn(Graphghan selectedGraphghan) {
        try {
            Color color = selectColor();
            int col = getColumnInput();
            if (col < 0) {
                throw new Exception();
            }
            selectedGraphghan.changeColorEntireColumn(color, col);
        } catch (Exception e) {
            System.out.println("Error changing, please try again");
        }
        printGraphghan(selectedGraphghan);
    }

    private void fillGraphghan(Graphghan selectedGraphghan) {
        try {
            Color color = selectColor();
            selectedGraphghan.changeColorEntireGraphghan(color);
        } catch (Exception e) {
            System.out.println("Error changing, please try again");
        } finally {
            printGraphghan(selectedGraphghan);
        }
    }

    private void seeColourOptions() {
        for (Color color : Color.values()) {
            System.out.println(color.getColorCode() + color.name() + "⬛ " + "\u001B[0m");
        }
    }

    private int getRowInput() {
        System.out.println("What row?");
        int row = input.nextInt();
        row = row - 1;
        return row;
    }

    private int getColumnInput() {
        System.out.println("What column? ");
        int column = input.nextInt();
        column = column - 1;
        return column;

    }
}
