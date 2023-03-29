package ui.gui;

import model.Graphghan;
import model.GraphghanSquare;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;

// CLASS COMMENT: Class representing an internal frame that will
//                display a selected project to edit, and process
//                and display all interactions for editing

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

    // EFFECTS: Constructs and initializes the fields for an editing frame
    //          for the given graphghan to be displayed on the given
    //          crochet parent application
    public EditingFrame(Graphghan g, CrochetApp2 parent) {
        this.parent = parent;
        this.editingWindow = new JInternalFrame(g.getName(), false, true);
        this.currColor = DARK_TEAL;
        this.graphghan = g;
    }

    // MODIFIES: this
    // EFFECTS:  adds the editing frame to the parent application,
    //           and sets it to be visible
    public void addToScreen() {
        parent.add(editingWindow);
        editingWindow.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  performs all set-up operations for the editing frame,
    //           including adding tools, blanket panel, styling
    //           and setting the active tool to one square
    public void setUpEditingFrame() {
        styleFrame();
        addToolPanel();
        activeTool = this.toolPanel.getTool(0);
        addBlanketPanel();
    }

    // MODIFIES: this
    // EFFECTS:  initializes and sets up tool panel to be displayed
    //           on the top of the editing panel
    private void addToolPanel() {
        this.toolPanel = new ToolPanel(this);
        toolPanel.styleToolPanel();
        toolPanel.setUpToolPanel();
        toolPanel.addToEditingWindow();
    }

    // MODIFIES: this
    // EFFECTS:  performs all styling operations for the editing window,
    //           and associates frame with an editing frame listener
    private void styleFrame() {
        editingWindow.addInternalFrameListener(new EditingFrameListener());
        editingWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        editingWindow.setLayout(new BorderLayout());
        editingWindow.setBackground(BRIGHT_TEAL);
        editingWindow.setOpaque(true);
        editingWindow.setBorder(BorderFactory.createLineBorder(DARK_TEAL, BORDER_WIDTH));
    }

    // CLASS COMMENT: Internal frame listener for editing frames that
    //                will detect when internal frame is closed
    private class EditingFrameListener implements InternalFrameListener {

        @Override
        // NO EFFECTS: Default operation is to do nothing
        public void internalFrameOpened(InternalFrameEvent e) {
            //
        }

        @Override
        // NO EFFECTS: Default operation is to do nothing
        public void internalFrameClosing(InternalFrameEvent e) {
            //
        }

        @Override
        // MODIFIES: parent
        // EFFECTS:  when frame is closed, informs the parent component
        //           that the project panel should be re-displayed
        public void internalFrameClosed(InternalFrameEvent e) {
            parent.updateGraphghanList();
        }

        @Override
        // NO EFFECTS: Default operation is to do nothing
        public void internalFrameIconified(InternalFrameEvent e) {
            //
        }

        @Override
        // NO EFFECTS: Default operation is to do nothing
        public void internalFrameDeiconified(InternalFrameEvent e) {
            //
        }

        @Override
        // NO EFFECTS: Default operation is to do nothing
        public void internalFrameActivated(InternalFrameEvent e) {
            //
        }

        @Override
        // NO EFFECTS: Default operation is to do nothing
        public void internalFrameDeactivated(InternalFrameEvent e) {
            //
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds the given component to the editing frame
    //           in the given position
    public void add(Component component, String position) {
        this.editingWindow.add(component, position);
    }

    // MODIFIES: this, parent
    // EFFECTS:  if editing action is detected, processes editing action
    //           through the model based on current active tool, and then
    //           updates and repaints the editing frame and parent
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

    // MODIFIES: this
    // EFFECTS:  initializes and sets up blanket panel to be displayed
    //           on the top of the editing panel for the selected graphghan
    public void addBlanketPanel() {
        this.blanketPanel = new BlanketPanel(this, graphghan);
        blanketPanel.styleBlanketPanel();
        blanketPanel.addSquaresToPanel();
        blanketPanel.addToEditingWindow();
    }

    // MODIFIES: this
    // EFFECTS:  revalidates and repaints all container components
    //           in the editing frame
    public void updateVisuals() {
        blanketPanel.revalidate();
        blanketPanel.repaint();
        toolPanel.revalidate();
        toolPanel.repaint();
        editingWindow.revalidate();
        editingWindow.repaint();
    }

    // MODIFIES: this
    // EFFECTS:  deactivates the currently active tool and actives
    //           the given tool
    public void setActiveTool(EditTool t) {
        if (t != null) {
            if (activeTool != null) {
                activeTool.deactivate();
            }
            t.activate();
            activeTool = t;
        }
    }

    // MODIFIES: this
    // EFFECTS:  if color change is selected, displays a JColorChooser
    //           in a JOptionPane, and then passes the selected color to
    //           the tool panel. Also sets current color to that color
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
