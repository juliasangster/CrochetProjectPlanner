package model;

public class Graphghan {
    private final GraphghanSquare[][] squares;
    private String name;
    private final int rows;
    private final int columns;


    // EFFECTS: Constructs a graphghan of size (rows * columns)
    //          with a constructed graphghan square at every
    //          combination of row index and column index and given name.
    //          All graphghan squares are made white by default.
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

    // TODO: Remove?
    // MODIFIES: this
    // EFFECTS:  Changes the name to the given name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the total number of rows in the graphghan
    public int getRows() {
        return rows;
    }

    // EFFECTS: returns the total number of columns in the graphghan
    public int getColumns() {
        return columns;
    }

    // EFFECTS: Returns the size of the
    public int getSize() {
        return columns * rows;
    }

    // REQUIRES: row <= (this.rows - 1)
    //           column <= (this.columns - 1)
    // EFFECTS: Graphghan square at the specified row index (row) and column
    //          index (column) will be returned. Note it will not be removed
    //          from the array, only the reference will return.
    public GraphghanSquare getSquare(int row, int column) {
        return squares[row][column];
    }


    public void changeColorSingleSquare(Color color, int row, int column) {
        GraphghanSquare graphghanSquare = getSquare(row,column);
        graphghanSquare.changeColor(color);
    }

    public void changeColorEntireRow(Color color, int row) {
        for (GraphghanSquare[] squareRow : squares) {
            for (GraphghanSquare square: squareRow) {
                if (square.getRow() == row) {
                    square.changeColor(color);
                }
            }
        }
    }

    public void changeColorEntireColumn(Color color, int column) {
        for (GraphghanSquare[] squareRow: squares) {
            for (GraphghanSquare square: squareRow) {
                if (square.getColumn() == column) {
                    square.changeColor(color);
                }
            }
        }
    }

    public void changeColorEntireGraphghan(Color color) {
        for (GraphghanSquare[] squareRow : squares) {
            for (GraphghanSquare square : squareRow) {
                square.changeColor(color);
            }
        }
    }

}
