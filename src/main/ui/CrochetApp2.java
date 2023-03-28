package ui;

import model.Graphghan;
import model.GraphghanSquare;
import model.ProjectCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;
import static javax.swing.BoxLayout.Y_AXIS;

// CLASS COMMENT: represents a state of a crochet application
// with a ProjectCollection and inputs via Scanner

public class CrochetApp2 extends JFrame {
    private static final String JSON_STORE = "./data/projects.json";
    private ProjectCollection projects;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Dimension appSize;
    private Color currColor;
    private Boolean oneSquare;
    private Boolean fill;
    private Boolean column;
    private Boolean row;

    private EditingFrame ceWindow;

    private EditTool activeTool;

    private static final int MAX_CHOICE_LOOPS = 5;

    protected static Color GREY = new Color(216,216,216);
    private static final Color PEACH = new Color(228,147,147);
    private static final Color DARK_TEAL = new Color(36,89,83);
    private static final Color BRIGHT_TEAL = new Color(64,142,145);
    private JPanel mainMenu;
    private JPanel dcpPanel;
    private JInternalFrame ceWindow2;
    private ArrayList<JButton> activeEditingButtons;

    // EFFECTS: runs the application
    public CrochetApp2() throws IOException {
        super("Crochet App");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        appSize = dimensionsOfScreen(); // Gives reference for all elements
        setWindowAesthetics();
        projects = new ProjectCollection();
        mainMenu = setUpMainMenu();
        currColor = getRandomColor();
        setUpBooleans();
        activeEditingButtons = new ArrayList<>();
//        JPanel graphghanPanel = new JPanel();
//        graphghanPanel.setSize(dimensionOfPanel());
//        graphghanPanel.setVisible(true);
//        graphghanPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        graphghanPanel.add(new GraphghanSquarePanel(new GraphghanSquare(1,1), graphghanPanel));
//        graphghanPanel.add(new GraphghanSquarePanel(new GraphghanSquare(1,1), graphghanPanel));
//        this.add(graphghanPanel, BorderLayout.CENTER);

        //JInternalFrame graphghanEditor = graphghanEditor(new Graphghan("yeet",100,100));
        //this.add(graphghanEditor);
        //graphghanEditor.setVisible(true);
        //Likely temp, saving incase useful later
        //JPanel square = new GraphghanSquarePanel();
        //this.add(square, BorderLayout.CENTER);

        //displayCurrentProjectsLoaded();
        pack();

    }

    private void setUpBooleans() {
        this.oneSquare = true;
        this.fill = false;
        this.row = false;
        this.column = false;
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

    private JLabel createAppIcon() throws IOException {
        BufferedImage image = ImageIO.read(new File("./data/crochet_app_imp.png"));
        JLabel appIcon = new JLabel(new ImageIcon(image));
        appIcon.setMaximumSize(dimensionOfPanel());
        return appIcon;
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
            processNewGraphghan();
            updateGraphghanList();
        }
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
            int result = processSave();
            if (result == JOptionPane.OK_OPTION) {
                saveProjectCollection();
            }
        }
    }

    private int processSave() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                "Save Projects", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    private int processLoad() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?",
                "Load Projects", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    private class LoadButtonActionListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            int result = processLoad();
            if (result == JOptionPane.OK_OPTION) {
                loadProjectCollection();
                updateGraphghanList();
            }
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

    private JInternalFrame graphghanEditor(Graphghan g) {
        JInternalFrame graphEditor = new JInternalFrame(g.getName(), false, true);
        graphEditor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        graphEditor.setLayout(new BorderLayout());
        graphEditor.setBorder(BorderFactory.createLineBorder(DARK_TEAL, 10));
        JPanel toolPanel = createToolPanel();
        graphEditor.add(toolPanel, BorderLayout.NORTH);
        JPanel blanketPanel = new JPanel();
        blanketPanel.setLayout(new GridLayout(g.getRows(), g.getColumns()));

        for (GraphghanSquare[] squareRow : g.getSquares()) {
            for (GraphghanSquare square : squareRow) {
                addSquareButton(square, blanketPanel, g);
            }
        }

        graphEditor.add(blanketPanel);

        return graphEditor;

    }
    // TODO: Remove suppress warnings

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})

    private JPanel createToolPanel() {

        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new GridLayout(1,0));

        JButton oneSquare = new JButton("One Square");
        oneSquare.addActionListener(new EnableOneSquareActionListener(oneSquare, this.oneSquare));

        JButton fill = new JButton("Fill");
        fill.addActionListener(new EnableFillActionListener(fill, this.fill));

        JButton changeCol = new JButton("Column");
        changeCol.addActionListener(new EnableColumnActionListener(changeCol, this.column));

        JButton changeRow = new JButton("Row");
        changeRow.addActionListener(new EnableRowActionListener(changeRow, this.row));

        ButtonGroup tools = new ButtonGroup();

        tools.add(oneSquare);
        tools.add(fill);
        tools.add(changeCol);
        tools.add(changeRow);

        toolPanel.add(oneSquare);
        toolPanel.add(fill);
        toolPanel.add(changeCol);
        toolPanel.add(changeRow);

        JLabel currColor = new JLabel();
        currColor.setBackground(this.currColor);
        currColor.setVisible(true);
        currColor.setOpaque(true);
        currColor.setMinimumSize(new Dimension(10,10));

        toolPanel.add(currColor);

        JButton changeColor = new JButton("Change Color");
        changeColor.addActionListener(new ChangeColorActionListener(currColor));

        toolPanel.add(changeColor);

        return toolPanel;

    }

    private class EnableFillActionListener implements ActionListener {

        private JButton button;
        private Boolean enabled;

        public EnableFillActionListener(JButton button, Boolean enabled) {
            this.button = button;
            this.enabled = enabled;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (enabled == false) {
                button.setEnabled(true);
                enabled = true;

                oneSquare = false;
                fill = true;
                column = false;
                row = false;
            }
        }
    }

    private class EnableColumnActionListener implements ActionListener {

        private JButton button;
        private Boolean enabled;

        public EnableColumnActionListener(JButton button, Boolean enabled) {
            this.button = button;
            this.enabled = enabled;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (enabled == false) {
                button.setEnabled(true);
                enabled = true;

                oneSquare = false;
                fill = false;
                column = true;
                row = false;
            }
        }
    }

    private class EnableRowActionListener implements ActionListener {

        private JButton button;
        private Boolean enabled;

        public EnableRowActionListener(JButton button, Boolean enabled) {
            this.button = button;
            this.enabled = enabled;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (enabled == false) {
                button.setEnabled(true);
                enabled = true;

                oneSquare = false;
                fill = false;
                column = false;
                row = true;
            }
        }
    }


    private class EnableOneSquareActionListener implements ActionListener {

        private JButton button;
        private Boolean enabled;

        public EnableOneSquareActionListener(JButton button, Boolean enabled) {
            this.button = button;
            this.enabled = enabled;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (enabled == false) {
                button.setEnabled(true);
                enabled = true;

                oneSquare = true;
                fill = false;
                column = false;
                row = false;
            }
        }
    }


    private class ChangeColorActionListener implements ActionListener {

        private JLabel currColor;

        public ChangeColorActionListener(JLabel currColor) {
            super();
            this.currColor = currColor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color newColor = processColorChange();
            currColor.setBackground(newColor);
            repaint();
        }
    }

    private Color processColorChange() {
        JColorChooser jcc = new JColorChooser(Color.WHITE);
        int result = JOptionPane.showConfirmDialog(this, jcc, "Color Selection", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Color newColor = jcc.getColor();
            currColor = newColor;
        }
        return currColor;
    }

    private void addSquareButton(GraphghanSquare g, JPanel graphEditor, Graphghan currGraphghan) {
        JButton button = new JButton();
        button.setBackground(g.getColor());
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(GREY));
        button.setFocusPainted(false);
        button.addActionListener(new GraphghanSquareActionListener(g, currGraphghan, button));
        graphEditor.add(button);
        activeEditingButtons.add(button);
    }

    private class GraphghanSquareActionListener implements ActionListener {

        private GraphghanSquare square;
        private Graphghan graphghan;
        private JButton button;

        public GraphghanSquareActionListener(GraphghanSquare grSquare, Graphghan graphghan, JButton button) {
            super();
            this.square = grSquare;
            this.graphghan = graphghan;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (oneSquare) {
                graphghan.changeColorSingleSquare(currColor, square.getRow(), square.getColumn());
                button.setBackground(currColor);
                revalidate();
                repaint();
            } else if (fill) {
                graphghan.changeColorEntireGraphghan(currColor);
                button.setBackground(currColor);
                for (JButton button: activeEditingButtons) {
                    button.setBackground(currColor);
                }
            }
        }
    }

    public Color getRandomColor() {
        Random r = new Random();
        int red = r.nextInt(250);
        int green = r.nextInt(250);
        int blue = r.nextInt(250);
        Color random = new Color(red, green, blue);
        return random;
    }

    //drawing player v1
    public void setActiveTool(EditTool tool) {
        if (activeTool != null) {
            activeTool.deactivate();
        }
        tool.activate();
        activeTool = tool;
    }

    private void displayCurrentProjectsLoaded() throws IOException {
        dcpPanel = new JPanel();
        dcpPanel.setBackground(DARK_TEAL);
        dcpPanel.setLayout(new BorderLayout());
        dcpPanel.setBorder(BorderFactory.createLineBorder(DARK_TEAL, 50));
        JLabel mainInfo = mainInfo();

        JPanel grPanel = new JPanel();
        grPanel.setLayout(new BoxLayout(grPanel, Y_AXIS));
        grPanel.setBackground(DARK_TEAL);
        grPanel.setForeground(Color.WHITE);

        grPanel.add(mainInfo);
        for (Graphghan g : projects) {
            JLabel grLabel = new JLabel();
            grLabel.setText("- " + g.getName());
            grLabel.setFont(new Font("MONOSPACED", BOLD, 20));
            grLabel.setForeground(Color.WHITE);
            grPanel.add(grLabel);
        }
        grPanel.setVisible(true);
        dcpPanel.add(grPanel);
        this.add(dcpPanel, BorderLayout.CENTER);

        JLabel appIcon = new JLabel();
        ImageIcon image  = new ImageIcon("./data/crochet_app_imp.png");
        appIcon.setIcon(image);
        dcpPanel.add(appIcon, BorderLayout.EAST);

    }

    private JLabel mainInfo() {
        JLabel main = new JLabel();
        main.setFont(new Font("MONOSPACED", BOLD, 20));
        main.setForeground(Color.WHITE);
        main.setText("Projects:");
        return main;
    }

    private void updateGraphghanList() {
        dcpPanel.removeAll();
        JLabel mainInfo = mainInfo();


        JPanel grPanel = new JPanel();
        grPanel.setLayout(new BoxLayout(grPanel, Y_AXIS));
        grPanel.setBackground(DARK_TEAL);

        grPanel.add(mainInfo);
        for (Graphghan g : projects) {
            JLabel grLabel = new JLabel();
            grLabel.setText("- " + g.getName());
            grLabel.setFont(new Font("MONOSPACED", BOLD, 20));
            grLabel.setForeground(Color.WHITE);
            grPanel.add(grLabel);
        }
        grPanel.setVisible(true);
        dcpPanel.add(grPanel);
        this.add(dcpPanel, BorderLayout.CENTER);
        dcpPanel.setVisible(true);
        dcpPanel.revalidate();
        dcpPanel.repaint();
    }


}



