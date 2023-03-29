package ui;

import model.Graphghan;
import model.ProjectCollection;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

public class ProjectsPanel {

    private JPanel container;

    private JLabel appIcon;

    private JPanel listContainer;
    private JPanel projectList;
    private JLabel mainInfo;

    private ProjectCollection projects;

    private static final Color DARK_TEAL = new Color(36,89,83);

    public ProjectsPanel(ProjectCollection projects) {
        this.projects = projects;
        this.container = new JPanel();
        this.listContainer = new JPanel();
        this.projectList = new JPanel();
        this.mainInfo = new JLabel();
        container.setVisible(false);
    }

    public JPanel getContainer() {
        return this.container;
    }

    public void setVisible(Boolean b) {
        container.setVisible(b);
        listContainer.setVisible(b);
        projectList.setVisible(b);
        mainInfo.setVisible(b);
    }

    public void styleProjectsPanel() {
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setBackground(DARK_TEAL);
        container.setBorder(BorderFactory.createLineBorder(DARK_TEAL, 50));
        container.setOpaque(true);
    }

    public void setUpProjectsPanel() {

        setVisible(true);

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

        appIcon = generateAppIcon();
        container.add(appIcon);
        appIcon.setAlignmentY(Component.TOP_ALIGNMENT);

    }

    public void updateProjectList() {
        projectList.removeAll();
        for (Graphghan g : projects) {
            JLabel grLabel = new JLabel();
            grLabel.setText("- " + g.getName());
            grLabel.setFont(new Font("MONOSPACED", BOLD, 20));
            grLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            grLabel.setForeground(Color.WHITE);
            grLabel.setBackground(DARK_TEAL);
            projectList.add(grLabel);
        }
    }

    public JLabel generateAppIcon() {
        JLabel appIcon = new JLabel();
        ImageIcon image  = new ImageIcon("./data/crochet_app_imp.png");
        appIcon.setIcon(image);
        return appIcon;
    }

    public void setProjects(ProjectCollection projects) {
        this.projects = projects;
    }

    public void update(ProjectCollection projects) {
        this.projects = projects;
        updateProjectList();
        container.revalidate();
        container.repaint();
    }

    private JLabel generateMainInfo() {
        JLabel main = new JLabel();
        main.setFont(new Font("MONOSPACED", BOLD, 20));
        main.setForeground(Color.WHITE);
        main.setText("Projects:");
        return main;
    }


}
