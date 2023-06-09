package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// CLASS COMMENT: Class representing a single graphghan square (the smallest unit in a graphghan) with its color
// and location in the blanket (in row index and column index)

public class GraphghanSquare implements Writable {
    private Color color;
    private final int row;
    private final int column;

    // REQUIRES: row >= 1 && column >= 1
    // EFFECTS: creates a graphghan square with a given row index (row),
    //          column index (column), and white color
    public GraphghanSquare(int row, int column) {
        this.row = row;
        this.column = column;
        this.color = Color.WHITE;
    }

    // EFFECTS: returns the color of the given graphghan square
    public Color getColor() {
        return this.color;
    }

    // MODIFIES: this.color
    // EFFECTS:  changes the color of square to given newColor
    public void changeColor(Color newColor) {
        this.color = newColor;
    }

    // EFFECTS: returns the row index of the square
    public int getRow() {
        return this.row;
    }

    // EFFECTS: returns the column index of the  square
    public int getColumn() {
        return this.column;
    }

    // EFFECTS: IF: the given color (color) is the same as this.color, return true.
    //          ELSE: return false.
    public boolean isGivenColor(Color color) {
        return this.color.equals(color);
    }

    @Override
    // EFFECTS: returns this as a Json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("red", color.getRed());
        json.put("green", color.getGreen());
        json.put("blue", color.getBlue());
        json.put("row", row);
        json.put("col", column);
        return json;
    }

}
