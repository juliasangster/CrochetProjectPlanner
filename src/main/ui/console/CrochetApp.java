//package ui.console;
//
//// SOURCES:
////   - TellerApplication: https://github.students.cs.ubc.ca/CPSC210/TellerApp
////       - Used to determine structure of many UI functions in this application
//
//import model.ConsoleColor;
//import model.Graphghan;
//import model.GraphghanSquare;
//import model.ProjectCollection;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//// CLASS COMMENT: represents a state of a crochet application
//// with a ProjectCollection and inputs via Scanner
//
//public class CrochetApp {
//    private static final String JSON_STORE = "./data/projects.json";
//    private ProjectCollection projects;
//    private Scanner input;
//    private JsonReader jsonReader;
//    private JsonWriter jsonWriter;
//
//    private static final int MAX_CHOICE_LOOPS = 5;
//
//    // EFFECTS: runs the application
//    public CrochetApp() {
//        runCrochetApp();
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  initializes an empty Project Collection (list of projects),
//    //           and the Scanner object needed to run the application
//    private void setupCrochetApp() {
//        projects = new ProjectCollection();
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//        jsonReader = new JsonReader(JSON_STORE);
//        jsonWriter = new JsonWriter(JSON_STORE);
//    }
//
//
//    // EFFECTS: Displays a welcome message
//    public static void welcomeMessage() {
//        System.out.println("\n\u001b[35m" + "Welcome to the crochet app!" + "\u001B[0m");
//        System.out.println("\u001b[35m" + "Please select from the following options:" + "\u001B[0m");
//    }
//
//    // EFFECTS: Displays a menu of main menu options for user
//    public static void startMenuOptions() {
//        System.out.println("\n\tn -> create new project");
//        System.out.println("\td -> delete an existing project");
//        System.out.println("\te -> edit a current project");
//        System.out.println("\tl -> see a list of all projects");
//        System.out.println("\ts -> save current state");
//        System.out.println("\to -> load previous state");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  displays initial welcome. collects and processes the user inputs from the main menu of the
//    //           crochet application
//    public void runCrochetApp() {
//        boolean runApp = true;
//        String keyInput;
//
//        setupCrochetApp();
//        welcomeMessage();
//
//        while (runApp) {
//            startMenuOptions();
//            keyInput = input.next();
//            keyInput = keyInput.toLowerCase();
//
//            if (keyInput.equals("q")) {
//                runApp = false;
//            } else {
//                processKeyInput(keyInput);
//            }
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  processes user inputs from the main menu
//    public void processKeyInput(String keyInput) {
//        if (keyInput.equals("n")) {
//            newGraphghan();
//        } else if (keyInput.equals("e")) {
//            editGraphghan();
//        } else if (keyInput.equals("l")) {
//            viewGraphghanList();
//        } else if (keyInput.equals("d")) {
//            deleteGraphghan();
//        } else if (keyInput.equals("s")) {
//            saveProjectCollection();
//        } else if (keyInput.equals("o")) {
//            loadProjectCollection();
//        } else {
//            System.out.println("Your choice is not valid.");
//            System.out.println("Please select again");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  loads workroom from file
//    private void loadProjectCollection() {
//        try {
//            projects = jsonReader.read();
//            System.out.println("Successfully loaded file!!");
//        } catch (IOException e) {
//            System.out.println("Did not load file correctly.");
//        }
//
//
//    }
//
//    // EFFECTS: saves the workroom to file
//    private void saveProjectCollection() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(projects);
//            jsonWriter.close();
//            System.out.println("Saved current state");
//        } catch (FileNotFoundException e) {
//            System.out.println("ERROR!");
//        }
//
//    }
//
//    // EFFECTS:  Prints a single block character representing a Graphghan
//    //           square to the console, in the associated color
//    public static void printColourBlock(GraphghanSquare graphghanSquare) {
//        System.out.print(graphghanSquare.getColor().getColorCode() + "⬛ " + "\u001B[0m");
//    }
//
//    // EFFECTS: Prints the given row of Graphghan Squares in the
//    //          associated color for each square
//    public static void printRow(GraphghanSquare[] squareRow) {
//        for (GraphghanSquare square: squareRow) {
//            printColourBlock(square);
//        }
//        System.out.println(" ");
//    }
//
//    // REQUIRES: graphghan to print is not null
//    // EFFECTS:  prints a coloured-block representation of the
//    //           graphghan to the console
//    public static void printGraphghan(Graphghan graphghan) {
//
//        for (GraphghanSquare[] squareRow : graphghan.getSquares()) {
//            printRow(squareRow);
//        }
//    }
//
//    // REQUIRES: no graphghan with duplicate names in this.projects
//    // MODIFIES: this
//    // EFFECTS:  processes deletion of graphghan from this.projects. Prompts user to select graphghan by
//    //           name. Returns message indicating success of removal operation.
//    private void deleteGraphghan() {
//        try {
//            Graphghan selected = selectGraphghan();
//            String nameSelected = selected.getName();
//            boolean result = projects.removeProject(nameSelected);
//            assert !(projects.containsGivenProject(nameSelected));
//            if (result) {
//                System.out.println("Successfully removed " + nameSelected + "\n");
//            } else {
//                System.out.println("Could not remove " + nameSelected + "\n");
//                System.out.println("Please double-check list of projects\n");
//            }
//        } catch (Exception e) {
//            System.out.println("Could not remove\n");
//            System.out.println("Please double-check list of projects\n");
//        }
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  prompts user for a name, and number of columns and rows of a new project.
//    //           processes the addition of this new project to this.projects.
//    //           handles exceptional cases (rows/columns < 1, and previous
//    //           name given) by printing error message to users and not adding graphghan
//    private void newGraphghan() {
//        try {
//            System.out.print("What would you like to name your new project?\n");
//            String name = input.next();
//
//            System.out.print("How many rows?\n");
//            int rows = input.nextInt();
//            System.out.print("How many columns?\n");
//            int columns = input.nextInt();
//
//            ArrayList<String> namesGiven = projects.getAllNames();
//
//            if (rows < 1 || columns < 1 || namesGiven.contains(name)) {
//                throw new Exception();
//            }
//
//            projects.addProject(name, rows, columns);
//
//            System.out.print("Your new graphghan " + name + " has been added to projects");
//        } catch (Exception e) {
//            this.input = new Scanner(System.in);
//            System.out.print("Failed to add graphghan, please try again");
//        }
//
//
//    }
//
//    // EFFECTS: IF the project list is empty:
//    //              - returns a message to user indicating the list is empty
//    //          ELSE: prints information (name, dimensions) of all
//    //          graphghans currently stored in projects
//    private void viewGraphghanList() {
//        if (projects.isEmpty()) {
//            System.out.println("There are no graphghans to show\n");
//        } else {
//            for (Graphghan g : projects) {
//                System.out.println("Graphghan: " + g.getName());
//                System.out.println("Dimensions " + g.getRows() + " X " + g.getColumns() + "\t[ROW X COL]");
//                System.out.println("============================");
//            }
//        }
//        System.out.println("Returning you to the main menu");
//    }
//
//    // EFFECTS: prompts the user to select a graphghan in the list
//    //          and returns it.
//    //          prints success message and graphghan once selected
//    private Graphghan selectGraphghan() throws Exception {
//        String selection = ""; // forces entry into loop
//        boolean runLoop = false;
//        int loopCounter = 0;
//
//        while (!runLoop) {
//            System.out.println("Please write the exact name of the project.");
//            System.out.println("The names of your active projects are below:");
//            for (Graphghan g : projects) {
//                System.out.println("- " + g.getName());
//            }
//            selection = input.next();
//            runLoop = projects.containsGivenProject(selection);
//            loopCounter++;
//            if (loopCounter >= MAX_CHOICE_LOOPS) {
//                throw new Exception();
//            }
//        }
//
//        System.out.println("You have selected" + selection);
//
//        return projects.getGraphghanSpecificName(selection);
//
//    }
//
//    // EFFECTS: IF list of projects is empty:
//    //              - returns a message that editing cannot be completed
//    //          ELSE:
//    //              - prompts the user to select a graphghan to edit and
//    //                then processes editing if selection successful
//    private void editGraphghan() {
//        try {
//            if (projects.isEmpty()) {
//                System.out.println("There are no graphghans to select. Please add a new project.");
//            } else {
//                Graphghan selectedGraphghan = selectGraphghan();
//                printGraphghan(selectedGraphghan);
//                editGraphghanAllOptions(selectedGraphghan);
//            }
//        } catch (Exception e) {
//            System.out.println("You failed to select a graphghan to edit.");
//            System.out.println("Returning to main menu.");
//        }
//    }
//
//    // REQUIRES: selectedGraphghan is not null
//    // EFFECTS: processes editing inputs and outputs to the console while editing, after selecting
//    // graphghan ("e" selected from main menu)
//    private void editGraphghanAllOptions(Graphghan selectedGraphghan) {
//        editingOptions();
//
//        boolean editing = true;
//
//        String editCommand = input.next();
//
//        while (editing) {
//            selectEditingTool(editCommand,
//                        selectedGraphghan);
//            boolean choice;
//            try {
//                System.out.println("Continue editing?");
//                choice = continueOrStop();
//            } catch (Exception e) {
//                choice = false;
//            }
//            if (choice) {
//                editingOptions();
//                editCommand = input.next();
//            }
//            editing = choice;
//        }
//        System.out.println("You have selected to finish editing. Returning to main menu.");
//    }
//
//    // EFFECTS: prints list of editing tool options (editing menu)
//    private void editingOptions() {
//        System.out.println("\t sqr -> Change colour of 1 square");
//        System.out.println("\t row -> Change colour of 1 row");
//        System.out.println("\t col -> Change colour of 1 column");
//        System.out.println("\t fil -> Change colour entire graphghan");
//        System.out.println("\t opt -> See colour options");
//        System.out.println("\t q   -> Quit editing");
//    }
//
//    // REQUIRES: selectedGraphghan, editCommand is not null
//    // EFFECTS: processes user inputs when in editing menu when a repeated tool
//    //          has not yet been selected
//    private void selectEditingTool(String editCommand, Graphghan selectedGraphghan) {
//
//        boolean runLoop = true;
//        try {
//            while (runLoop) {
//                if (editCommand.matches("sqr|row|col|fil|opt")) {
//                    editCommand = editingToolsThatRepeat(editCommand, selectedGraphghan);
//                } else if (editCommand.equals("opt")) {
//                    seeColourOptions();
//                    System.out.println("See again?");
//                    runLoop = continueOrStop();
//                } else if (editCommand.equals("q")) {
//                    runLoop = false;
//                } else {
//                    System.out.println("Selection was not valid. Try again?");
//                    runLoop = continueOrStop();
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("ERROR");
//        }
//    }
//
//    // REQUIRES: selectedGraphghan, editCommand is not null
//    // EFFECTS:  processes editing inputs and outputs for tools that the
//    //           user wants to repeatedly use without returning to editing main
//    //           menu.
//    private String editingToolsThatRepeat(String editCommand, Graphghan selectedGraphghan) {
//
//        boolean runLoop = true;
//
//        while (runLoop) {
//            if (editCommand.equals("sqr")) {
//                changeSquare(selectedGraphghan);
//            } else if (editCommand.equals("row")) {
//                changeRow(selectedGraphghan);
//            } else if (editCommand.equals("col")) {
//                changeColumn(selectedGraphghan);
//            } else if (editCommand.equals("fil")) {
//                fillGraphghan(selectedGraphghan);
//            } else if (editCommand.equals("opt")) {
//                seeColourOptions();
//            }
//            System.out.println("Use same tool?");
//            runLoop = continueOrStop();
//        }
//        return "q";
//    }
//
//    // REQUIRES: selectedGraphghan is not null
//    // MODIFIES: this
//    // EFFECTS:  processes a single-square color change to selected Graphghan
//    //           prompts user for color and row/column index input
//    //           handles all exceptional cases by asking user to retry operation if
//    //           inputs would cause error in downstream methods and throwing checked exception
//    //           prints representation of graphghan after operation succeeded/failed
//    private void changeSquare(Graphghan selectedGraphghan) {
//
//        try {
//            Color color = selectColor();
//            int row = getRowInput();
//            int column = getColumnInput();
//            if (row < 0 || column < 0) {
//                throw new Exception();
//            }
//            selectedGraphghan.changeColorSingleSquare(color, row, column);
//        } catch (Exception e) {
//            System.out.println("Error changing, please try again");
//        } finally {
//            printGraphghan(selectedGraphghan);
//        }
//    }
//
//    // EFFECTS: prompts user to select a yes/no input for continuing an operation.
//    //          if user inputs "Y" or "y", return true
//    //          if user inputs "N" or "n", return false
//    //          if user fails to select an appropriate operation for more than
//    //          the designated maximum (MAX_CHOICE_LOOPS), returns false
//    private boolean continueOrStop() {
//
//        for (int loopCount = 0; loopCount < MAX_CHOICE_LOOPS; loopCount++) {
//            System.out.print("Y/N?");
//            String choice = input.next();
//            choice = choice.toUpperCase();
//            if (choice.equals("Y")) {
//                System.out.println("Selected Y");
//                return true;
//            } else if (choice.equals("N")) {
//                System.out.println("Selected N");
//                return false;
//            }
//        }
//        System.out.println("Selection failed, returning");
//        return false;
//    }
//
//    // EFFECTS: prompts user to select a color from the ANSI color options
//    //          if user appropriately picks a color, return color
//    //          if user fails to select an appropriate operation for more than
//    //          the designated maximum (MAX_CHOICE_LOOPS), throws checked exception
//    private Color selectColor() throws Exception {
//
//        String colorChoice = ""; // forces entry into loop
//        boolean runLoop = true;
//        Color output = null;
//        int loopCounter = 0;
//
//        while (runLoop) {
//            System.out.println("What colour would you like?");
//            colorChoice = input.next();
//            colorChoice = colorChoice.toUpperCase();
//            for (Color color : Color.values()) {
//                if (colorChoice.equals(color.name())) {
//                    output = color;
//                    runLoop = false;
//                }
//            }
//            loopCounter++;
//
//            if (loopCounter >= MAX_CHOICE_LOOPS) {
//                throw new Exception();
//            }
//        }
//        return output;
//    }
//
//    // REQUIRES: selectedGraphghan is not null
//    // MODIFIES: this
//    // EFFECTS:  processes a single-row color change to selectedGraphghan
//    //           prompts user for color and row index input
//    //           handles all exceptional cases by asking user to retry operation if
//    //           inputs would cause error in downstream methods and throwing checked exception
//    //           prints representation of graphghan after operation succeeded/failed
//    private void changeRow(Graphghan selectedGraphghan) {
//        try {
//            Color color = selectColor();
//            int row = getRowInput();
//            if (row < 0) {
//                throw new Exception();
//            }
//            selectedGraphghan.changeColorEntireRow(color, row);
//        } catch (Exception e) {
//            System.out.println("Error changing, please try again");
//        } finally {
//            printGraphghan(selectedGraphghan);
//        }
//    }
//
//    // REQUIRES: selectedGraphghan is not null
//    // MODIFIES: this
//    // EFFECTS:  processes a single-column color change to selectedGraphghan
//    //           prompts user for color and column index input
//    //           handles all exceptional cases by asking user to retry operation if
//    //           inputs would cause error in downstream methods and throwing checked exception
//    //           prints representation of graphghan after operation succeeded/failed
//    private void changeColumn(Graphghan selectedGraphghan) {
//        try {
//            Color color = selectColor();
//            int col = getColumnInput();
//            if (col < 0) {
//                throw new Exception();
//            }
//            selectedGraphghan.changeColorEntireColumn(color, col);
//        } catch (Exception e) {
//            System.out.println("Error changing, please try again");
//        } finally {
//            printGraphghan(selectedGraphghan);
//        }
//    }
//
//    // REQUIRES: selectedGraphghan is not null
//    // MODIFIES: this
//    // EFFECTS:  processes a full color change to selectedGraphghan
//    //           prompts user for color
//    //           handles exceptional case of failing to select color by asking user to retry operation if
//    //           inputs would cause error in downstream methods and throwing checked exception
//    //           prints representation of graphghan after operation succeeded/failed
//    private void fillGraphghan(Graphghan selectedGraphghan) {
//        try {
//            Color color = selectColor();
//            selectedGraphghan.changeColorEntireGraphghan(color);
//        } catch (Exception e) {
//            System.out.println("Error changing, please try again");
//        } finally {
//            printGraphghan(selectedGraphghan);
//        }
//    }
//
//    // EFFECTS: prints a list of all ANSI color options available to select for graphghan
//    //          squares to the console in their associated color.
//    private void seeColourOptions() {
//        for (Color color : Color.values()) {
//            System.out.println(color.getColorCode() + color.name() + "⬛ " + "\u001B[0m");
//        }
//    }
//
//    // EFFECTS: processes the row index input for all color-change operations
//    private int getRowInput() {
//        System.out.println("What row?");
//        int row = input.nextInt();
//        row = row - 1;
//        return row;
//    }
//
//    // EFFECTS: processes the column index input for all color-change operations
//    private int getColumnInput() {
//        System.out.println("What column? ");
//        int column = input.nextInt();
//        column = column - 1;
//        return column;
//
//    }
//}
