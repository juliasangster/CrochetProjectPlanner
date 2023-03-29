package ui;

import model.Graphghan;

// stolen heavily from control and data flow drawing player v1

import javax.swing.*;
import java.awt.*;

public abstract class EditTool {

    protected JButton button;
    protected ToolPanel toolPanel;

    private static final Color LIGHT_TEAL = new Color(202,231,232);

    private boolean active;

    public EditTool(ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
        createButton(toolPanel);
        addToToolPanel(toolPanel);
        active = false;
        addListener();
    }

    protected void addToToolPanel(ToolPanel toolPanel) {
        toolPanel.add(this);
    }

    protected JButton styleButton(JButton button) {
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        button.setBackground(Color.WHITE);
        button.setFont(new Font("SANS SERIF", Font.BOLD, 16));
        button.setFocusPainted(true);
        button.setOpaque(true);
        return button;
    }

    public void repaint() {
        button.revalidate();
        button.repaint();
    }

    public void revalidate() {
        button.revalidate();
        button.repaint();
    }

    public boolean isActive() {
        return this.active;
    }

    public void activate() {
        this.active = true;
        button.setBackground(LIGHT_TEAL);
    }

    public void deactivate() {
        this.active = false;
        button.setBackground(Color.WHITE);
    }

    protected abstract void createButton(ToolPanel toolPanel);

    protected abstract void addListener();

    public JButton getButton() {
        return this.button;
    }

}
