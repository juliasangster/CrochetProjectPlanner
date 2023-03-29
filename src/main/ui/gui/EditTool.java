package ui.gui;

import javax.swing.*;
import java.awt.*;

// SOURCE: EditTool (and all inheritors) heavily inspired from
//         "Simple Drawing Player"
// https://github.students.cs.ubc.ca/CPSC210/Control-And-Data-Flow-Lecture-Starters/commit/bec34954a3104a1e334827d3968be0cef073f9e1

// CLASS COMMENT: Class representing an editing tool, with an associated
//                button for interaction, and the tool panel it will be displayed on
public abstract class EditTool {

    private static final Color LIGHT_TEAL = new Color(202,231,232);

    protected JButton button;
    protected ToolPanel toolPanel;

    // EFFECTS: Constructs an editing tool with a button for interaction
    public EditTool(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
        createButton(toolPanel);
        addToToolPanel(toolPanel);
        addListener();
    }

    // MODIFIES: this, toolPanel
    // EFFECTS:  adds the editing tool to the tool panel
    protected void addToToolPanel(ToolPanel toolPanel) {
        toolPanel.add(this);
    }

    // MODIFIES: this
    // EFFECTS:  applies all customization operations to button
    protected JButton styleButton(JButton button) {
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        button.setBackground(Color.WHITE);
        button.setFont(new Font("SANS SERIF", Font.BOLD, 16));
        button.setFocusPainted(true);
        button.setOpaque(true);
        return button;
    }

    // MODIFIES: this
    // EFFECTS:  repaints and revalidates button
    public void repaint() {
        button.revalidate();
        button.repaint();
    }

    // MODIFIES: this
    // EFFECTS:  repaints and revalidates button
    public void revalidate() {
        button.revalidate();
        button.repaint();
    }

    // MODIFIES: this
    // EFFECTS:  changes the background color of tool button to a
    //           light-teal color to show user which tool is activated
    public void activate() {
        button.setBackground(LIGHT_TEAL);
    }

    // MODIFIES: this
    // EFFECTS:  changes the background color of tool button to a
    //           white color to show user tool is deactivated
    public void deactivate() {
        button.setBackground(Color.WHITE);
    }

    // MODIFIES: this
    // EFFECTS:  creates a JButton for the tool, and styles
    //           button for ToolPanel
    protected abstract void createButton(ToolPanel toolPanel);

    // EFFECTS: adds an action listener for this tool
    protected abstract void addListener();

    // EFFECTS: returns the button for the tool
    public JButton getButton() {
        return this.button;
    }

}
