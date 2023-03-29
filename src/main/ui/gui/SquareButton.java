package ui.gui;

import model.GraphghanSquare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CLASS COMMENT: Class representing a single graphghan square in blanket
//                editing panel, with associated square and button for
//                interacting with graphghan square

public class SquareButton {

    protected static Color GREY = new Color(216,216,216);

    private BlanketPanel parent;
    private JButton button;
    private GraphghanSquare square;

    // EFFECTS: Constructs a square button for the given
    //          graphghan square to be displayed on given parent panel
    public SquareButton(GraphghanSquare square, BlanketPanel parent) {
        this.button = new JButton();
        this.square = square;
        this.parent = parent;
        styleButton();
        button.addActionListener(new GraphghanSquareActionListener(square));
    }

    // MODIFIES: this
    // EFFECTS:  styles the button and colors the button based on
    //           color associated with square
    public void styleButton() {
        button.setBackground(square.getColor());
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(GREY));
        button.setFocusPainted(false);
    }

    // EFFECTS: returns the button associated with the square
    public JButton getButton() {
        return this.button;
    }

    // MODIFIES: this
    // EFFECTS:  updates the color of the square button to the current
    //           color stored in model square, and then repaints to
    //           display new color
    public void repaint() {
        button.setBackground(square.getColor());
        button.repaint();
    }

    // EFFECTS: revalidates the button to update display to GUI
    public void revalidate() {
        button.revalidate();
    }

    // CLASS COMMENT: Action listener for interactions with graphghan
    //                square button in blanket panel
    private class GraphghanSquareActionListener implements ActionListener {

        private GraphghanSquare graphghanSquare;

        // EFFECTS: constructs the action listener and stores the
        //          information for the associated square
        public GraphghanSquareActionListener(GraphghanSquare g) {
            super();
            this.graphghanSquare = g;
        }

        @Override
        // MODIFIES: parent
        // EFFECTS:  informs parent panel that an action has been registered
        //           in blanket panel, and passes information about square pressed
        public void actionPerformed(ActionEvent e) {
            parent.processAction(graphghanSquare);
        }
    }




}
