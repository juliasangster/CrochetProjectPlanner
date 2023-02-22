package model;

public class GraphghanSquare {
    private Color color;
    private int row;
    private int column;

    // EFFECTS: Creates a graphghan square with a given row index (row),
    //          column index (column), and white color
    public GraphghanSquare(int row, int column) {
        this.row = row;
        this.column = column;
        this.color = Color.WHITE;
    }

    // EFFECTS: Returns the color of the given graphghan square
    public Color getColor() {
        return this.color;
    }

    // REQUIRES: newColor is a member of model.Color
    // MODIFIES: this.color
    // EFFECTS:  Updates the color parameter to be a new color
    public void changeColor(Color newColor) {
        this.color = newColor;
    }

    // EFFECTS: Returns the row index of the given square
    public int getRow() {
        return this.row;
    }

    // EFFECTS: Returns the column index of the given square
    public int getColumn() {
        return this.column;
    }

    public boolean isGivenColor(Color color) {
        return this.color == color;
    }


}
