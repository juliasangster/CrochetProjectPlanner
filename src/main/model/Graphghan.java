package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// CLASS COMMENT: Class representing a graphghan blanket, with its number of squares along the rows (rows, >= 0),
// number of squares along the columns (columns, >= 0), a name (non-empty), and a list of graphghan squares contained
// in the blanket

public class Graphghan implements Writable {
    private GraphghanSquare[][] squares;
    private final String name;
    private final int rows;
    private final int columns;


    // EFFECTS: constructs a graphghan of with the correct number of rows/columns (size = rows * columns)
    //          with a constructed graphghan square at every combination of row index and column index and given name.
    //          all graphghan squares are made white by default.
    public Graphghan(String name, int rows, int columns) {
        this.squares = new GraphghanSquare[rows][columns];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                squares[j][i] = new GraphghanSquare(j,i);
            }
        }

        this.name = name;
        this.rows = rows;
        this.columns = columns;

    }

    // EFFECTS: returns name of graphghan
    public String getName() {
        return name;
    }

    // EFFECTS: returns the total number of rows in the graphghan
    public int getRows() {
        return rows;
    }

    // EFFECTS: returns the total number of columns in the graphghan
    public int getColumns() {
        return columns;
    }

    // EFFECTS: returns the size of the graphghan in units of total number of graphghan squares
    public int getSize() {
        return columns * rows;
    }

    // EFFECTS: returns array of all graphghan squares for the given graphghan
    public GraphghanSquare[][] getSquares() {
        return this.squares;
    }

    // REQUIRES: row <= (this.rows - 1) && column <= (this.columns - 1)
    // EFFECTS: graphghan square at the specified row index (row) and column
    //          index (column) will be returned. Note it will not be removed from squares.
    public GraphghanSquare getSquare(int row, int column) {

        return squares[row][column];

    }

    // MODIFIES: this.squares
    // REQUIRES: row <= (this.rows - 1) && column <= (this.columns - 1)
    // EFFECTS:  changes the color of the graphghan at the given index to the given color
    public void changeColorSingleSquare(Color color, int row, int column) {
        GraphghanSquare graphghanSquare = getSquare(row,column);
        graphghanSquare.changeColor(color);
    }

    // MODIFIES: this.squares
    // REQUIRES: row <= (this.rows - 1)
    // EFFECTS: changes all graphghan squares in the given row to the given color
    public void changeColorEntireRow(Color color, int row) {
        for (GraphghanSquare[] squareRow : squares) {
            for (GraphghanSquare square: squareRow) {
                if (square.getRow() == row) {
                    square.changeColor(color);
                }
            }
        }
    }

    // MODIFIES: this.squares
    // REQUIRES: column <= (this.columns - 1)
    // EFFECTS: changes all graphghan squares in the given column to the given color
    public void changeColorEntireColumn(Color color, int column) {
        for (GraphghanSquare[] squareRow: squares) {
            for (GraphghanSquare square: squareRow) {
                if (square.getColumn() == column) {
                    square.changeColor(color);
                }
            }
        }
    }

    // MODIFIES: this.squares
    // EFFECTS: changes the color of all graphghan squares a graphghan to the given color
    public void changeColorEntireGraphghan(Color color) {
        for (GraphghanSquare[] squareRow : squares) {
            for (GraphghanSquare square : squareRow) {
                square.changeColor(color);
            }
        }
    }

    @Override
    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rows", rows);
        json.put("columns", columns);
        json.put("squares", squaresToJson());
        return json;
    }

    // EFFECTS: returns the graphghan squares for this as a JSON array
    private JSONArray squaresToJson() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                jsonArray.put(squares[j][i].toJson());
            }
        }

        return jsonArray;

    }

    // MODIFIES: this
    // EFFECTS: changes squares to the given squares
    public void setSquares(GraphghanSquare[][] squares) {
        this.squares = squares;
    }

    @Override
    // EFFECTS: returns the name of the graphghan
    public String toString() {
        return this.name;
    }
}
