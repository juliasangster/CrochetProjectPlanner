package ui.gui;

import model.Graphghan;
import model.ProjectCollection;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

// CLASS COMMENT: Class representing a panel of all current projects
//                loaded in the application, which will be updated
//                periodically as the app runs

public class ProjectsPanel {

    private static final Color DARK_TEAL = new Color(36,89,83);

    private JPanel container;
    private JLabel appIcon;
    private JPanel listContainer;
    private JPanel projectList;
    private JLabel mainInfo;
    private ProjectCollection projects;

    // EFFECTS: Constructs and initializes a project panel with
    //          associated projects list to display
    public ProjectsPanel(ProjectCollection projects) {
        this.projects = projects;
        this.container = new JPanel();
        this.listContainer = new JPanel();
        this.projectList = new JPanel();
        this.mainInfo = new JLabel();
        container.setVisible(false);
    }

    // EFFECTS: returns the highest-level container so that it can
    //          be added to crochet application
    public JPanel getContainer() {
        return this.container;
    }

    // MODIFIES: this
    // EFFECTS:  sets all elements in project panel to be visible
    public void setVisible(Boolean b) {
        container.setVisible(b);
        listContainer.setVisible(b);
        projectList.setVisible(b);
        mainInfo.setVisible(b);
    }

    // MODIFIES: this
    // EFFECTS:  performs all styling operations for highest level container of
    //           project panel
    public void styleProjectsPanel() {
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setBackground(DARK_TEAL);
        container.setBorder(BorderFactory.createLineBorder(DARK_TEAL, 50));
        container.setOpaque(true);
    }

    // REQUIRES: this method has never been called prior
    // MODIFIES: this
    // EFFECTS:  styles, sets up and adds all lower-level containers
    //           to the project panel, including an updated project
    //           list, the unchanging label, and an app icon
    public void setUpProjectsPanel() {
        setVisible(true);
        appIcon = generateAppIcon();
        container.add(appIcon);
        appIcon.setAlignmentY(Component.TOP_ALIGNMENT);
        mainInfo = generateMainInfo();
        mainInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        listContainer.add(mainInfo);
        updateProjectList();
        projectList.setLayout(new BoxLayout(projectList,BoxLayout.Y_AXIS));
        projectList.setAlignmentX(Component.LEFT_ALIGNMENT);
        projectList.setBackground(DARK_TEAL);
        listContainer.add(projectList);
        listContainer.setBackground(DARK_TEAL);
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        container.add(listContainer);
        listContainer.setAlignmentY(Component.TOP_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS:  regenerates all the JLabels for individual graphghans
    //           in the project list, to reflect changes in model
    public void updateProjectList() {
        projectList.removeAll();
        for (Graphghan g : projects) {
            JLabel grLabel = new JLabel();
            grLabel.setText("- " + g.getName() + "   [ " + g.getRows() + " X " + g.getColumns() + " ]");
            grLabel.setFont(new Font("MONOSPACED", BOLD, 20));
            grLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            grLabel.setForeground(Color.WHITE);
            grLabel.setBackground(DARK_TEAL);
            projectList.add(grLabel);
        }
    }

    // EFFECTS: generates and returns a JLabel that is the application
    //          icon (crochet hook + ball of yarn) to be displayed
    //          next to project list
    public JLabel generateAppIcon() {
        JLabel appIcon = new JLabel();
        ImageIcon image  = new ImageIcon("./data/crochet_app_imp.png");
        appIcon.setIcon(image);
        appIcon.setBorder(BorderFactory.createLineBorder(DARK_TEAL, 20));
        return appIcon;
    }

    // MODIFIES: this
    // EFFECTS:  updates the projects list to the given projects list,
    //           then regenerates project list and repaints all elements
    public void update(ProjectCollection projects) {
        this.projects = projects;
        updateProjectList();
        container.revalidate();
        container.repaint();
    }

    // EFFECTS: generates and returns a JLabel that is the title
    //          for the projects panel
    private JLabel generateMainInfo() {
        JLabel main = new JLabel();
        main.setFont(new Font("MONOSPACED", BOLD, 20));
        main.setForeground(Color.WHITE);
        main.setText("Projects:");
        return main;
    }


}
