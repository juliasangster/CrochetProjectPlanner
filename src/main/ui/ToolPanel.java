package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToolPanel {

    private static final Color DARK_TEAL = new Color(36,89,83);
    private static final int BORDER_WIDTH = 4;

    private EditingFrame parent;
    private JPanel container;
    private Color currColor;
    private ArrayList<EditTool> tools;
    private JLabel currentColorLabel;
    private JButton changeColorButton;

    public enum ToolOptions {
        ONE_SQUARE,
        FILL,
        COLUMN,
        ROW
    }

    public ToolPanel(EditingFrame parent) {
        this.parent = parent;
        this.container = new JPanel();
        this.currColor = DARK_TEAL;
        this.tools = new ArrayList<>();
    }

    public EditTool getTool(int index) {
        return tools.get(index);
    }

    public void addToEditingWindow() {
        parent.add(container, BorderLayout.NORTH);
        container.setVisible(true);
    }

    public void setUpToolPanel() {
        addEditToolButtons();
        this.currentColorLabel = createColorLabel();
        this.container.add(currentColorLabel);

        this.changeColorButton = createChangeColorButton();
        this.container.add(changeColorButton);
    }

    public void styleToolPanel() {
        container.setLayout(new GridLayout(1,0));
        container.setBorder(BorderFactory.createLineBorder(DARK_TEAL, BORDER_WIDTH));
    }

    public void addEditToolButtons() {
        EditTool oneSquare = new OneSquareEditTool(this);
        EditTool fill = new FillEditTool(this);
        EditTool column = new ColumnFillEditTool(this);
        EditTool row = new RowFillEditTool(this);

        parent.setActiveTool(oneSquare);

    }

    public void add(EditTool editTool) {
        JButton button = editTool.getButton();
        container.add(button);
        tools.add(editTool);
    }

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

    public void repaint() {
        container.repaint();
        for (EditTool t: tools) {
            t.repaint();
        }
    }

    public void revalidate() {
        container.revalidate();
        for (EditTool t: tools) {
            t.revalidate();
        }
    }

    public JLabel createColorLabel() {
        JLabel currColor = new JLabel();
        currColor.setBackground(DARK_TEAL);
        currColor.setVisible(true);
        currColor.setOpaque(true);
        currColor.setMinimumSize(new Dimension(10,10));
        return currColor;
    }

    public void updateColorLabel() {
        currentColorLabel.setBackground(currColor);
        currentColorLabel.revalidate();
        currentColorLabel.repaint();
    }

    public void setCurrentColor(Color c) {
        this.currColor = c;
    }

    public JButton createChangeColorButton() {
        JButton changeColor = new JButton("Change Color");
        changeColor.setContentAreaFilled(true);
        changeColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        changeColor.setBackground(Color.WHITE);
        changeColor.setFont(new Font("SANS SERIF", Font.BOLD, 16));
        changeColor.setFocusPainted(true);
        changeColor.setOpaque(true);
        changeColor.addActionListener(new ChangeColorActionListener());
        return changeColor;
    }

    private class ChangeColorActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.processColorChange();
            updateColorLabel();
        }
    }



}
