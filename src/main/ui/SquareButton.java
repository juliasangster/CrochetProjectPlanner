package ui;

import model.GraphghanSquare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareButton {

    private BlanketPanel parent;
    private JButton button;
    private GraphghanSquare square;


    protected static Color GREY = new Color(216,216,216);

    public SquareButton(GraphghanSquare square, BlanketPanel parent) {
        this.button = new JButton();
        this.square = square;
        this.parent = parent;
        styleButton();
        button.addActionListener(new GraphghanSquareActionListener(square));
    }

    public void styleButton() {
        button.setBackground(square.getColor());
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(GREY));
        button.setFocusPainted(false);
    }

    public JButton getButton() {
        return this.button;
    }

    public void repaint() {
        button.setBackground(square.getColor());
        button.repaint();
    }

    public void revalidate() {
        button.revalidate();
    }

    private class GraphghanSquareActionListener implements ActionListener {

        private GraphghanSquare graphghanSquare;

        public GraphghanSquareActionListener(GraphghanSquare g) {
            super();
            this.graphghanSquare = g;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            parent.processAction(graphghanSquare);
            System.out.println("You pressed " + graphghanSquare.getRow() + "X " + graphghanSquare.getColumn());
            System.out.println(graphghanSquare.getColor());
        }
    }




}
