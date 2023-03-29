package ui;

import model.Graphghan;
import model.GraphghanSquare;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class EditingFrame {

    private CrochetApp2 parent;
    private JInternalFrame editingWindow;
    private Graphghan graphghan;
    private ToolPanel toolPanel;
    private BlanketPanel blanketPanel;
    private Color currColor;
    private EditTool activeTool;

    private static final Color BRIGHT_TEAL = new Color(64,142,145);
    private static final Color DARK_TEAL = new Color(36,89,83);
    private static final int BORDER_WIDTH = 4;

    public EditingFrame(Graphghan g, CrochetApp2 parent) {
        this.parent = parent;
        this.editingWindow = new JInternalFrame(g.getName(), false, true);
        this.currColor = DARK_TEAL;
        this.graphghan = g;
    }

    public void addToScreen() {
        parent.add(editingWindow);
        editingWindow.setVisible(true);
    }

    public void setUpEditingFrame() {
        styleFrame();
        addToolPanel();
        activeTool = this.toolPanel.getTool(0);
        addBlanketPanel();
    }

    private void addToolPanel() {
        this.toolPanel = new ToolPanel(this);
        toolPanel.styleToolPanel();
        toolPanel.setUpToolPanel();
        toolPanel.addToEditingWindow();
    }

    private void styleFrame() {
        editingWindow.addInternalFrameListener(new EditingFrameListener());
        editingWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        editingWindow.setLayout(new BorderLayout());
        editingWindow.setBackground(BRIGHT_TEAL);
        editingWindow.setOpaque(true);
        editingWindow.setBorder(BorderFactory.createLineBorder(DARK_TEAL, BORDER_WIDTH));
    }

    private class EditingFrameListener implements InternalFrameListener {

        @Override
        public void internalFrameOpened(InternalFrameEvent e) {
            //
        }

        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
            //
        }

        @Override
        public void internalFrameClosed(InternalFrameEvent e) {
            parent.updateGraphghanList();
        }

        @Override
        public void internalFrameIconified(InternalFrameEvent e) {
            //
        }

        @Override
        public void internalFrameDeiconified(InternalFrameEvent e) {
            //
        }

        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            //
        }

        @Override
        public void internalFrameDeactivated(InternalFrameEvent e) {
            //
        }
    }

    public void add(Component component, String position) {
        this.editingWindow.add(component, position);
    }

    public void processEditAction(GraphghanSquare g) {
        if (activeTool.getButton().getText().equals("One Square")) {
            graphghan.changeColorSingleSquare(currColor, g.getRow(), g.getColumn());
        } else if (activeTool.getButton().getText().equals("Fill")) {
            graphghan.changeColorEntireGraphghan(currColor);
        } else if (activeTool.getButton().getText().equals("Fill Row")) {
            graphghan.changeColorEntireRow(currColor, g.getRow());
        } else if (activeTool.getButton().getText().equals("Fill Column")) {
            graphghan.changeColorEntireColumn(currColor, g.getColumn());
        }
        updateVisuals();
        parent.revalidate();
        parent.repaint();

    }

    public void processChangeToolAction() {

    }

    public void addBlanketPanel() {
        this.blanketPanel = new BlanketPanel(this, graphghan);
        blanketPanel.styleBlanketPanel();
        blanketPanel.addSquaresToPanel();
        blanketPanel.addToEditingWindow();
    }



    public Color getCurrentColor() {
        return this.currColor;
    }

    public void updateVisuals() {
        blanketPanel.revalidate();
        blanketPanel.repaint();
        toolPanel.revalidate();
        toolPanel.repaint();
        editingWindow.revalidate();
        editingWindow.repaint();
    }

    public void setActiveTool(EditTool t) {
        if (t != null) {
            if (activeTool != null) {
                activeTool.deactivate();
            }
            t.activate();
            activeTool = t;
        }
    }

    public void processColorChange() {
        JColorChooser jcc = new JColorChooser(Color.WHITE);
        int result = JOptionPane.showConfirmDialog(editingWindow, jcc, "Color Selection", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Color newColor = jcc.getColor();
            currColor = newColor;
            toolPanel.setCurrentColor(newColor);
        }
    }
}
