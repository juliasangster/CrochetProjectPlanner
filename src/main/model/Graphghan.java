package model;

import java.util.ArrayList;

public class Graphghan {
    private ArrayList<GraphghanSquare> squares;
    private String name;
    private int rows;
    private int columns;

    public Graphghan(String name, int rows, int columns) {
        this.squares = new ArrayList<>();

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                squares.add(new GraphghanSquare(i,j));
            }
        }

        this.name = name;
        this.rows = rows;
        this.columns = columns;

    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
