package ui;

import model.Graphghan;
import model.GraphghanSquare;
import ui.CrochetApp2;
import ui.EditingFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BlanketPanel {

    protected static Color GREY = new Color(216,216,216);

    private EditingFrame parent;
    private JPanel container;
    private Graphghan graphghan;
    private ArrayList<SquareButton> graphghanButtons;

    public BlanketPanel(EditingFrame parent, Graphghan graphghan) {
        this.parent = parent;
        this.graphghan = graphghan;
        this.container = new JPanel();
        graphghanButtons = new ArrayList<>();
    }

    public void addToEditingWindow() {
        parent.add(container, BorderLayout.CENTER);
        container.setVisible(true);
    }

    public void styleBlanketPanel() {
        container.setLayout(new GridLayout(graphghan.getRows(), graphghan.getColumns()));
    }

    public void addSquaresToPanel() {
        for (GraphghanSquare[] squareRow : graphghan.getSquares()) {
            for (GraphghanSquare square : squareRow) {
                SquareButton button = new SquareButton(square, this);
                graphghanButtons.add(button);
            }
        }
    }

    public void repaint() {
        for (SquareButton b: graphghanButtons) {
            b.repaint();
        }
        container.repaint();
    }

    public void revalidate() {
        for (SquareButton b: graphghanButtons) {
            b.revalidate();
        }
        container.revalidate();
    }

    public void processAction(GraphghanSquare g) {
        parent.processEditAction(g);
    }





}
