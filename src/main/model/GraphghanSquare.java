package model;

public class GraphghanSquare {
    private Colour colour;
    private int row;
    private int column;

    public GraphghanSquare(int row, int column) {
        this.colour = new Colour(0,0,0);
        this.row = row;
        this.column = column;
    }


    public void changeColour(Colour colour) {
        this.colour = colour;
    }

    public void clearColour() {
        this.colour = new Colour(0,0,0);
    }



}
