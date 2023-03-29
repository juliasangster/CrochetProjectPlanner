package ui;

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
import java.util.Random;

import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;

// CLASS COMMENT: represents a state of a crochet application
// with a ProjectCollection and inputs via Scanner

public class CrochetApp2 extends JFrame {
    private static final String JSON_STORE = "./data/projects.json";
    private ProjectCollection projects;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Dimension appSize;


    private EditingFrame ceWindow;

    private static final Color BRIGHT_TEAL = new Color(64,142,145);
    protected static Color GREY = new Color(216,216,216);
    private static final Color DARK_TEAL = new Color(36,89,83);


    private JPanel mainMenu;
    private ProjectsPanel projectsPanel;


    // EFFECTS: runs the application
    public CrochetApp2() throws IOException {
        super("Crochet App");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        appSize = dimensionsOfScreen(); // Gives reference for all elements
        setWindowAesthetics();
        projects = new ProjectCollection();
        mainMenu = setUpMainMenu();
        projectsPanel = new ProjectsPanel(projects);
        this.add(projectsPanel.getContainer());
        pack();
    }

    private JComboBox<Graphghan> createSelectGraphghanBox() {
        JComboBox<Graphghan> selectGraphghanBox = new JComboBox<>();
        for (Graphghan g: projects) {
            selectGraphghanBox.addItem(g);
        }
        selectGraphghanBox.setVisible(true);
        return selectGraphghanBox;
    }

    public Dimension getAppSize() {
        return this.appSize;
    }

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


    private Dimension dimensionsOfScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        double width = scrn.width * 0.9;
        double height = scrn.height * 0.9;
        int intWidth = (int) width;
        int intHeight = (int) height;
        return new Dimension(intWidth, intHeight);
    }

    //TODO: Cite; stolen from SpaceInvaders
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // EFFECTS: saves the workroom to file
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
    // EFFECTS:  loads workroom from file
    private void loadProjectCollection() {
        try {
            projects = jsonReader.read();
        } catch (IOException e) {
            //
        }


    }

    private Dimension dimensionOfPanel() {
        Dimension size = getAppSize();
        double width = size.width * 0.15;
        double height = size.height * 0.15;
        int intWidth = (int) width;
        int intHeight = (int) height;
        Dimension mainMenuSize = new Dimension(intWidth, intHeight);
        return mainMenuSize;
    }

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

    private void setUpButtons() {
        createSaveButton();
        createLoadButton();
        createNewGraphghanButton();
        createDeleteGraphghanButton();
        createClearProjectsButton();
        createEditGraphghanButton();
        createQuitAppButton();

    }


    private JLabel createAppTitle() {
        JLabel appLabel = new JLabel("Graphghan Creator", SwingConstants.CENTER);
        appLabel.setFont(new Font("SANS SERIF", BOLD | ITALIC, 20));
        appLabel.setBackground(Color.WHITE);
        appLabel.setOpaque(true);
        appLabel.setForeground(DARK_TEAL);
        appLabel.setBorder(BorderFactory.createLineBorder(BRIGHT_TEAL, 4));
        return appLabel;
    }

    protected void setMainMenuButtonStyle(JButton button) {
        button.setBackground(Color.WHITE);
        button.setFont(new Font("SANS SERIF", BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(DARK_TEAL, 4));
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setVisible(true);
        button.setOpaque(true);
    }

    private JButton createMainMenuButton(String buttonString) {
        JButton button = new JButton(buttonString);
        setMainMenuButtonStyle(button);
        mainMenu.add(button);
        return button;
    }

    private void createNewGraphghanButton() {
        JButton newGraphghanButton = createMainMenuButton("New Project");
        newGraphghanButton.addActionListener(new NewGraphghanActionListener());
    }

    private class NewGraphghanActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (projects.isEmpty()) {
                initializeProjectsPanel();
            }
            processNewGraphghan();
            updateGraphghanList();
        }
    }

    public void initializeProjectsPanel() {
        projectsPanel.setVisible(true);
        projectsPanel.styleProjectsPanel();
        projectsPanel.setUpProjectsPanel();
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})

    private void processNewGraphghan() {
        JPanel ngPanel = new JPanel();
        JTextField nameField = new JTextField(20);
        JSlider rows = new JSlider(JSlider.HORIZONTAL, 0,100, 50);
        JSlider columns = new JSlider(JSlider.HORIZONTAL, 0,100, 50);
        JLabel rowLabel = new JLabel("Rows");
        JLabel colLabel = new JLabel("Columns");
        JLabel nameLabel = new JLabel("Name");
        rows.setMajorTickSpacing(50);
        rows.setMinorTickSpacing(10);
        rows.setPaintLabels(true);
        columns.setMajorTickSpacing(50);
        columns.setMinorTickSpacing(10);
        columns.setPaintLabels(true);
        rows.setPaintTicks(true);
        columns.setPaintTicks(true);
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


    private void createEditGraphghanButton() {
        JButton editButton = createMainMenuButton("Edit Project");
        editButton.addActionListener(new StartEditActionListener());
    }

    private class StartEditActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Graphghan g = processSelectGraphghan();
            openEditingWindow(g);
            revalidate();
            repaint();
        }
    }

    private void openEditingWindow(Graphghan g) {
        projectsPanel.setVisible(false);
        ceWindow = new EditingFrame(g, this);
        ceWindow.setUpEditingFrame();
        ceWindow.addToScreen();
    }

    private Graphghan processSelectGraphghan() {
        JComboBox<Graphghan> selectBox = createSelectGraphghanBox();
        JPanel selectPanel = new JPanel();
        selectPanel.add(selectBox);
        Graphghan selectedGraphghan = null;
        int result = JOptionPane.showConfirmDialog(this, selectPanel,
                "Select Graphghan to Open", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selectedGraphghan = (Graphghan) selectBox.getSelectedItem();
        }
        return selectedGraphghan;

    }

    private void createDeleteGraphghanButton() {
        JButton deleteButton = createMainMenuButton("Delete Project");
        deleteButton.addActionListener(new DeleteActionListener());
    }

    private class DeleteActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            processDelete();
        }
    }

    private void processDelete() {

    }

    private void createClearProjectsButton() {
        JButton clearProjectsButton = createMainMenuButton("Clear Projects");
    }

    private void createSaveButton() {
        JButton saveButton = createMainMenuButton("Save");
        saveButton.addActionListener(new SaveButtonActionListener());

    }

    private class SaveButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            processSave();
        }
    }

    private void processSave() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                "Save Projects", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            saveProjectCollection();
            projectsPanel.update(projects);
        }
    }

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

    private class LoadButtonActionListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            processLoad();
        }
    }



    private class QuitButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = processQuit();
            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }



    private void createLoadButton() {
        JButton loadButton = createMainMenuButton("Load");
        loadButton.addActionListener(new LoadButtonActionListener());
    }

    private void createQuitAppButton() {
        JButton quitButton = createMainMenuButton("Quit");
        quitButton.addActionListener(new QuitButtonActionListener());
    }


    //https://stackoverflow.com/posts/24091786/edit
    private int processQuit() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public ProjectCollection getProjectCollection() {
        return this.projects;
    }

    public Color getRandomColor() {
        Random r = new Random();
        int red = r.nextInt(250);
        int green = r.nextInt(250);
        int blue = r.nextInt(250);
        Color random = new Color(red, green, blue);
        return random;
    }

    public void updateGraphghanList() {
        projectsPanel.update(projects);
        projectsPanel.setVisible(true);
        this.repaint();
    }


}



