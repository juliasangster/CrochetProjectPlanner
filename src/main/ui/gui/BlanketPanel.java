package ui.gui;

import model.Graphghan;
import model.GraphghanSquare;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// CLASS COMMENT: Class representing a container that holds the representation
//                of the graphghan during editing, and handles the processing
//                of inputs to the active editing window
//                Field graphghan in this class is the graphghan to be edited
//                Field graphghanButtons stores all buttons to be used on editing
//                frame, and stores while editing to update visuals on buttons.

public class BlanketPanel {

    private static final Color GREY = new Color(216,216,216);

    private EditingFrame parent;
    private JPanel container;
    private Graphghan graphghan;
    private ArrayList<SquareButton> graphghanButtons;

    // REQUIRES: graphghan is not null
    // EFFECTS:  Initializes fields of blanket panel, sets given graphghan
    //           as the graphghan to edit, and assigns the editing frame as
    //           the parent component.
    public BlanketPanel(EditingFrame parent, Graphghan graphghan) {
        this.parent = parent;
        this.graphghan = graphghan;
        this.container = new JPanel();
        graphghanButtons = new ArrayList<>();
    }

    // MODIFIES: this, parent
    // EFFECTS: adds the blanket panel to editing frame in the central
    //          location, and sets as visible to user
    public void addToEditingWindow() {
        parent.add(container, BorderLayout.CENTER);
        container.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  customizes the look of the blanket panel, and sets up
    //           grid layout in dimensions of given graphghan (rows by columns)
    public void styleBlanketPanel() {
        container.setLayout(new GridLayout(graphghan.getRows(), graphghan.getColumns()));
        container.setBorder(BorderFactory.createLineBorder(GREY, 4));
    }

    // MODIFIES: this
    // EFFECTS:  for the assigned graphghan to edit, places a SquareButton
    //           for each graphghan square in blanket, and adds button to
    //           graphghanButtons and container
    public void addSquaresToPanel() {
        for (GraphghanSquare[] squareRow : graphghan.getSquares()) {
            for (GraphghanSquare square : squareRow) {
                SquareButton button = new SquareButton(square, this);
                graphghanButtons.add(button);
                container.add(button.getButton());
            }
        }
    }

    // EFFECTS: repaints all elements in blanket panel
    public void repaint() {
        for (SquareButton b: graphghanButtons) {
            b.repaint();
        }
        container.repaint();
    }

    // EFFECTS: revalidates all elements in blanket panel
    public void revalidate() {
        for (SquareButton b: graphghanButtons) {
            b.revalidate();
        }
        container.revalidate();
    }

    // EFFECTS: passes an action listened from a square button from the
    //          blanket panel to the editing frame
    public void processAction(GraphghanSquare g) {
        parent.processEditAction(g);
    }


}
