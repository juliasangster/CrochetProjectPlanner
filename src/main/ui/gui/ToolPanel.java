package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// CLASS COMMENT: Class representing a panel of all editing tools
//                to be displayed at the top of each editing frame

public class ToolPanel {

    private static final Color DARK_TEAL = new Color(36,89,83);
    private static final int BORDER_WIDTH = 1;
    private static final Color BRIGHT_TEAL = new Color(64,142,145);

    private EditingFrame parent;
    private JPanel container;
    private Color currColor;
    private ArrayList<EditTool> tools;
    private JLabel currentColorLabel;
    private JButton changeColorButton;

    // ENUM: Represents all possible tool options in tool-selection codes
    //       that allows easier interaction with editing frame
    public enum ToolOptions {
        ONE_SQUARE,
        FILL,
        COLUMN,
        ROW
    }

    // EFFECTS: Constructs and initializes a tool panel to be displayed
    //          on the given editing frame parent component. Initially
    //          sets selected color to background color of app
    public ToolPanel(EditingFrame parent) {
        this.parent = parent;
        this.container = new JPanel();
        this.currColor = DARK_TEAL;
        this.tools = new ArrayList<>();
    }

    // EFFECTS: returns the editing tool at the given index of tools
    public EditTool getTool(int index) {
        return tools.get(index);
    }

    // MODIFIES: this
    // EFFECTS:  adds the tool panel to the north area of the editing
    //           frame and sets the tool panel to be visible
    public void addToEditingWindow() {
        parent.add(container, BorderLayout.NORTH);
        container.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  adds all interactive components to the tool panel, including
    //           editing tool selection buttons, current color display
    //           and the change color button
    public void setUpToolPanel() {
        addEditToolButtons();
        this.currentColorLabel = createColorLabel();
        this.container.add(currentColorLabel);
        this.changeColorButton = createChangeColorButton();
        this.container.add(changeColorButton);
    }

    // MODIFIES: this
    // EFFECTS:  performs all styling to the top-level container for
    //           tool panel
    public void styleToolPanel() {
        container.setLayout(new GridLayout(1,0));
        container.setBorder(BorderFactory.createLineBorder(DARK_TEAL, BORDER_WIDTH));
        container.setBackground(BRIGHT_TEAL);
        container.setOpaque(true);
    }

    // MODIFIES: this
    // EFFECTS:  processes the addition of all editing tools
    //           to the tool panel, and sets the active tool to
    //           be the one-square editing tool
    public void addEditToolButtons() {
        EditTool oneSquare = new OneSquareEditTool(this);
        new FillEditTool(this);
        new ColumnFillEditTool(this);
        new RowFillEditTool(this);
        parent.setActiveTool(oneSquare);
    }

    // MODIFIES: this
    // EFFECTS:  adds an editing tool selection button to the tool panel
    public void add(EditTool editTool) {
        JButton button = editTool.getButton();
        container.add(button);
        tools.add(editTool);
    }

    // MODIFIES: this
    // EFFECTS:  passes tool selects as tool option codes to the
    //           editing frame when active tool is changed by user
    public void setActiveTool(ToolOptions tool) {
        EditTool activeTool = null;
        if (tool == ToolOptions.ONE_SQUARE) {
            activeTool = tools.get(0);
        } else if (tool == ToolOptions.FILL) {
            activeTool = tools.get(1);
        } else if (tool == ToolOptions.COLUMN) {
            activeTool = tools.get(2);
        } else if (tool == ToolOptions.ROW) {
            activeTool = tools.get(3);
        }
        parent.setActiveTool(activeTool);
    }

    // MODIFIES: this
    // EFFECTS:  repaints all components in the tool panel
    public void repaint() {
        container.repaint();
        for (EditTool t: tools) {
            t.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS:  revalidates all components in the tool panel
    public void revalidate() {
        container.revalidate();
        for (EditTool t: tools) {
            t.revalidate();
        }
    }

    // EFFECTS: creates and returns a JLabel that will display a small
    //          box in the current color for the user
    public JLabel createColorLabel() {
        JLabel currColor = new JLabel();
        currColor.setBackground(DARK_TEAL);
        currColor.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        currColor.setVisible(true);
        currColor.setOpaque(true);
        currColor.setMinimumSize(new Dimension(10,10));
        return currColor;
    }

    // MODIFIES: this
    // EFFECTS:  updates the color label to be the current color stored
    //           in the variable currColor
    public void updateColorLabel() {
        currentColorLabel.setBackground(currColor);
        currentColorLabel.revalidate();
        currentColorLabel.repaint();
    }

    // MODIFIES: this
    // EFFECTS:  sets the current color to the given color
    public void setCurrentColor(Color c) {
        this.currColor = c;
    }

    // EFFECTS: creates and returns a button that is associated with a
    //          change color action listener, for all interactions by user
    //          to change current editing color
    public JButton createChangeColorButton() {
        JButton changeColor = new JButton("Change Color");
        changeColor.setContentAreaFilled(true);
        changeColor.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        changeColor.setBackground(Color.WHITE);
        changeColor.setFont(new Font("SANS SERIF", Font.BOLD, 16));
        changeColor.setFocusPainted(true);
        changeColor.setOpaque(true);
        changeColor.addActionListener(new ChangeColorActionListener());
        return changeColor;
    }

    // CLASS COMMENT: Action listener for processing color change actions
    private class ChangeColorActionListener implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS:  prompts user to select a new color and updates
        //           the color label to the selected color
        public void actionPerformed(ActionEvent e) {
            parent.processColorChange();
            updateColorLabel();
        }
    }



}
