package ui.gui;

import model.Graphghan;
import model.ProjectCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.awt.Font.BOLD;

// CLASS COMMENT: represents a crochet application with a graphical user interface
//                that can store multiple graphghans in a project collection. The user
//                can interact and change these blankets via editing frames/

public class CrochetApp2 extends JFrame {

    private static final Color BRIGHT_TEAL = new Color(64,142,145);
    private static final Color DARK_TEAL = new Color(36,89,83);
    private static final String JSON_STORE = "./data/projects.json";

    private ProjectCollection projects;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Dimension appSize;
    private EditingFrame editingFrame;
    private JPanel mainMenu;
    private ProjectsPanel projectsPanel;

    // EFFECTS: Constructs and runs a crochet application with no projects loaded
    public CrochetApp2() throws IOException {
        super("Crochet App");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        appSize = dimensionsOfScreen();
        setWindowAesthetics();
        projects = new ProjectCollection();
        mainMenu = setUpMainMenu();
        projectsPanel = new ProjectsPanel(projects);
        this.add(projectsPanel.getContainer());
        pack();
    }

    // EFFECTS: constructs and creates a JComboBox (drop-down box) with all
    //          graphghan names currently loaded in program
    private JComboBox<Graphghan> createSelectGraphghanBox() {
        JComboBox<Graphghan> selectGraphghanBox = new JComboBox<>();
        for (Graphghan g: projects) {
            selectGraphghanBox.addItem(g);
        }
        selectGraphghanBox.setVisible(true);
        return selectGraphghanBox;
    }

    // EFFECTS: returns the dimensions of the overall JFrame this app is displayed in
    public Dimension getAppSize() {
        return this.appSize;
    }

    // MODIFIES: this
    // EFFECTS:  styles and centres the main frame of the application
    public void setWindowAesthetics() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        getContentPane().setBackground(new Color(36, 89, 83));
        setMinimumSize(appSize);
        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setHgap(10);
        mainLayout.setVgap(10);
        this.setLayout(mainLayout);
        ImageIcon appIcon = new ImageIcon("./data/crochet_app_imp.png");
        setIconImage(appIcon.getImage());
        setVisible(true);
        centreOnScreen();
    }

    // EFFECTS: determines and returns the dimension of the screen the application
    //          is currently being displayed in
    private Dimension dimensionsOfScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width * 0.9;
        double height = screenSize.height * 0.9;
        int intWidth = (int) width;
        int intHeight = (int) height;
        return new Dimension(intWidth, intHeight);
    }

    // SOURCE: Method centreOnScreen from "Space Invaders" application
    // https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase/commit/a29bdc4920f7d5d1f3844454a1bc3c5a257cc56f
    // MODIFIES: this
    // EFFECTS:  centres the application on the users screen
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS:  saves the current project collection to file
    private void saveProjectCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(projects);
            jsonWriter.close();
            //
        } catch (FileNotFoundException e) {
            //
        }

    }

    // MODIFIES: this
    // EFFECTS:  loads project collection from file
    private void loadProjectCollection() {
        try {
            projects = jsonReader.read();
        } catch (IOException e) {
            //
        }
    }

    // EFFECTS: finds the proper dimensions for main menu based on the
    //          overall app size, and returns dimensions
    private Dimension dimensionOfPanel() {
        Dimension size = getAppSize();
        double width = size.width * 0.15;
        double height = size.height * 0.15;
        int intWidth = (int) width;
        int intHeight = (int) height;
        return new Dimension(intWidth, intHeight);
    }

    // MODIFIES: this
    // EFFECTS:  instantiates and does all styling for the
    //           main menu displayed on the left of the app,
    //           and then returns main menu as JLabel
    public JPanel setUpMainMenu() throws IOException {
        mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(0,1));
        mainMenu.setPreferredSize(dimensionOfPanel());
        mainMenu.setMaximumSize(dimensionOfPanel());
        mainMenu.setBackground(DARK_TEAL);
        mainMenu.setVisible(true);
        JLabel appLabel = createAppTitle();
        mainMenu.add(appLabel);
        setUpButtons();
        add(mainMenu, BorderLayout.WEST);
        return mainMenu;
    }

    // MODIFIES: this
    // EFFECTS:  creates all buttons to be displayed on main menu
    private void setUpButtons() {
        createSaveButton();
        createLoadButton();
        createNewGraphghanButton();
        createDeleteGraphghanButton();
        createClearProjectsButton();
        createEditGraphghanButton();
        createQuitAppButton();
    }

    // EFFECTS: generates the app title to be displayed on the top of the main menu
    private JLabel createAppTitle() {
        JLabel appLabel = new JLabel("Graphghan Creator", SwingConstants.CENTER);
        appLabel.setFont(new Font("SANS SERIF", BOLD, 20));
        appLabel.setBackground(Color.WHITE);
        appLabel.setOpaque(true);
        appLabel.setForeground(DARK_TEAL);
        appLabel.setBorder(BorderFactory.createLineBorder(BRIGHT_TEAL, 4));
        return appLabel;
    }

    // MODIFIES: button
    // EFFECTS:  styles the given button to the main menu button style, and sets to be visible
    private void setMainMenuButtonStyle(JButton button) {
        button.setBackground(Color.WHITE);
        button.setFont(new Font("SANS SERIF", BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(DARK_TEAL, 4));
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setVisible(true);
        button.setOpaque(true);
    }

    // EFFECTS: creates and returns a styled main menu button with the given string to be displayed on it
    private JButton createMainMenuButton(String buttonString) {
        JButton button = new JButton(buttonString);
        setMainMenuButtonStyle(button);
        mainMenu.add(button);
        return button;
    }

    // MODIFIES: this
    // EFFECTS:  creates the button for new graphghan interactions and associates it
    //           with a new graphghan action listener
    private void createNewGraphghanButton() {
        JButton newGraphghanButton = createMainMenuButton("New Project");
        newGraphghanButton.addActionListener(new NewGraphghanActionListener());
    }

    // CLASS COMMENT: Action listener for processing new graphghan additions to project collection
    private class NewGraphghanActionListener implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS:  when new graphghan button is pressed, processes addition of new graphghan
        //           and updates the graphghan panel
        public void actionPerformed(ActionEvent e) {
            if (projects.isEmpty()) {
                initializeProjectsPanel();
            }
            processNewGraphghan();
            updateGraphghanList();
        }
    }

    // MODIFIES: this
    // EFFECTS:  initializes the project panel the first time it needs to be generated
    //           styles panel and sets to visible
    public void initializeProjectsPanel() {
        projectsPanel.setVisible(true);
        projectsPanel.styleProjectsPanel();
        projectsPanel.setUpProjectsPanel();
    }

    // EFFECTS: generates a JSlider that can be set from 0 to 100, with initial
    //          value set to 50. Styles and returns this JSlider
    private JSlider createSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0,100, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        return slider;
    }

    // MODIFIES: this
    // EFFECTS:  processes the addition of a new graphghan through a JOptionPane
    //           in which user will be prompted to give the rows, columns, and
    //           name of the new graphghan to add to project collection
    private void processNewGraphghan() {
        JPanel ngPanel = new JPanel();
        JTextField nameField = new JTextField(20);
        JSlider rows = createSlider();
        JSlider columns = createSlider();
        JLabel rowLabel = new JLabel("Rows");
        JLabel colLabel = new JLabel("Columns");
        JLabel nameLabel = new JLabel("Name");
        ngPanel.setLayout(new GridLayout(0,2));
        ngPanel.add(nameLabel);
        ngPanel.add(nameField);
        ngPanel.add(rowLabel);
        ngPanel.add(rows);
        ngPanel.add(colLabel);
        ngPanel.add(columns);
        int result = JOptionPane.showConfirmDialog(this, ngPanel,
                "New Project Creation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int rowInput = rows.getValue();
            int colInput = columns.getValue();
            String nameInput = nameField.getText();
            projects.addProject(nameInput, rowInput, colInput);
        }
    }


    // MODIFIES: this
    // EFFECTS:  creates the button for starting editing interactions and associates it
    //           with a start editing action listener
    private void createEditGraphghanButton() {
        JButton editButton = createMainMenuButton("Edit Project");
        editButton.addActionListener(new StartEditActionListener());
    }

    // CLASS COMMENT: Action listener that responds to user requests to begin editing a blanket
    private class StartEditActionListener implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS:  prompts user to select a graphghan and then opens editing frame for
        //           selected graphghan.
        public void actionPerformed(ActionEvent e) {
            Graphghan g = processSelectGraphghan("Select Graphghan to Open");
            openEditingWindow(g);
            revalidate();
            repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS:  makes the project panel non-visible, and then generates and displays
    //           an editing frame for the given graphghan g
    private void openEditingWindow(Graphghan g) {
        projectsPanel.setVisible(false);
        editingFrame = new EditingFrame(g, this);
        editingFrame.setUpEditingFrame();
        editingFrame.addToScreen();
    }

    // EFFECTS: opens a JOptionPane with a JComboBox of all projects currently stored
    //          in the associated project collection. Returns the grapghan the user selects
    private Graphghan processSelectGraphghan(String title) {
        JComboBox<Graphghan> selectBox = createSelectGraphghanBox();
        JPanel selectPanel = new JPanel();
        selectPanel.add(selectBox);
        Graphghan selectedGraphghan = null;
        int result = JOptionPane.showConfirmDialog(this, selectPanel,
                title, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selectedGraphghan = (Graphghan) selectBox.getSelectedItem();
        }
        return selectedGraphghan;

    }

    // MODIFIES: this
    // EFFECTS:  creates the button for deleting graphghan interactions and associates it
    //           with a delete graphghan action listener
    private void createDeleteGraphghanButton() {
        JButton deleteButton = createMainMenuButton("Delete Project");
        deleteButton.addActionListener(new DeleteActionListener());
    }

    // CLASS COMMENT: Action listener that responds to a user request to delete a graphghan
    private class DeleteActionListener implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS:  processes the deletion when a user requests to delete a grapghan
        public void actionPerformed(ActionEvent e) {
            processDelete();
        }
    }

    // MODIFIES: this
    // EFFECTS:  prompts user to select a graphghan to delete and then deletes graphghan
    private void processDelete() {
        Graphghan g = processSelectGraphghan("Select Graphghan to Delete");
        projects.remove(g);
        assert !(projects.containsGivenProject(g.getName()));
        projectsPanel.update(projects);
    }

    // MODIFIES: this
    // EFFECTS:  creates the button for clearing all graphghan interactions and associates it
    //           with a clear projects action listener
    private void createClearProjectsButton() {
        JButton clearProjectsButton = createMainMenuButton("Clear Projects");
        clearProjectsButton.addActionListener(new ClearProjectsActionListener());
    }

    // CLASS COMMENT: Action listener that responds to user requests to clear all projects
    private class ClearProjectsActionListener implements ActionListener {

        @Override
        // EFFECTS: processes the clearing action when user selects to clear all grapghans
        public void actionPerformed(ActionEvent e) {
            processClear();
        }
    }

    // MODIFIES: this
    // EFFECTS:  requests the user to confirm that they would like to clear all projects. If user selects
    //           "OK" then all projects will be removed and the project panel will be updated
    private void processClear() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                "Clear All Projects", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            projects = new ProjectCollection();
            projectsPanel.update(projects);
        }
    }

    // MODIFIES: this
    // EFFECTS:  creates the button for saving all projects in application and associates it
    //           with a save action listener
    private void createSaveButton() {
        JButton saveButton = createMainMenuButton("Save");
        saveButton.addActionListener(new SaveButtonActionListener());

    }

    // CLASS COMMENT: Action listener that responds to user requests to save projects
    private class SaveButtonActionListener implements ActionListener {

        @Override
        // EFFECTS: processes a save when user requests to save projects
        public void actionPerformed(ActionEvent e) {
            processSave();
        }
    }

    // MODIFIES: this
    // EFFECTS:  requests the user to confirm that they would like to save projects. If user selects
    //           "OK" then all projects will be saved to JSON store
    private void processSave() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                "Save Projects", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            saveProjectCollection();
            projectsPanel.update(projects);
        }
    }

    // MODIFIES: this
    // EFFECTS:  requests the user to confirm that they would like to load projects. If user selects
    //           "OK" then all projects will be loaded from JSON store
    private void processLoad() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                "Load Projects", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (projects.isEmpty()) {
                initializeProjectsPanel();
            }
            loadProjectCollection();
            projectsPanel.update(projects);
        }
    }

    // CLASS COMMENT: Action listener that responds to user requests to load projects from file
    private class LoadButtonActionListener implements ActionListener {

        @Override
        // EFFECTS: processes load when user requests to load projects from file
        public void actionPerformed(ActionEvent e) {
            processLoad();
        }
    }


    // CLASS COMMENT: Action listener that responds to user requests to quit application
    private class QuitButtonActionListener implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS:  prompts user to confirm quit, and if OK is selected quits app with exit code 0
        public void actionPerformed(ActionEvent e) {
            int result = processQuit();
            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  creates the button for loading all projects in application and associates it
    //           with a load action listener
    private void createLoadButton() {
        JButton loadButton = createMainMenuButton("Load");
        loadButton.addActionListener(new LoadButtonActionListener());
    }

    // MODIFIES: this
    // EFFECTS:  creates the button for quitting application and associates it
    //           with a quit action listener
    private void createQuitAppButton() {
        JButton quitButton = createMainMenuButton("Quit");
        quitButton.addActionListener(new QuitButtonActionListener());
    }

    // SOURCE: used a tutorial from stack overflow to figure out how action listeners work
    // https://stackoverflow.com/posts/24091786/edit
    // EFFECTS: prompts user to confirm that they would like to quit the app, and returns the result
    //          as an integer to the quit action listener
    private int processQuit() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    // MODIFIES: this
    // EFFECTS:  passes projects to project panel in order to sync data. Then updates project list
    //           and sets it to visible, then displays the project list
    public void updateGraphghanList() {
        projectsPanel.update(projects);
        projectsPanel.setVisible(true);
        this.repaint();
    }

}



