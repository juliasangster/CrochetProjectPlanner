package tests;

import model.Graphghan;
import model.GraphghanSquare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CLASS COMMENT: Testing suite for model.Graphghan class

public class GraphghanTest {

    private final static String TEST_STRING_1 = "Test";
    private final static String TEST_STRING_2 = "WideBlanket";
    private final static String TEST_STRING_3 = "NarrowBlanket";

    private final static int TEST_ROW_1 = 10;
    private final static int TEST_COL_1 = 10;

    private final static int TEST_ROW_2 = 10;
    private final static int TEST_COL_2 = 15;

    private final static int TEST_ROW_3 = 20;
    private final static int TEST_COL_3 = 10;


    private Graphghan graphghan1;
    private Graphghan graphghan2;
    private Graphghan graphghan3;

    @BeforeEach

    public void graphghanTestSetup() {
        graphghan1 = new Graphghan(TEST_STRING_1, TEST_ROW_1, TEST_COL_1);
        graphghan2 = new Graphghan(TEST_STRING_2, TEST_ROW_2, TEST_COL_2);
        graphghan3 = new Graphghan(TEST_STRING_3, TEST_ROW_3, TEST_COL_3);
    }

    @Test
    // CASE: Constructing a graphghan where rows = columns
    public void graphghanConstructorTestSquare() {
        String name = graphghan1.getName();
        int rows = graphghan1.getRows();
        int columns = graphghan1.getColumns();

        int size = graphghan1.getSize();

        GraphghanSquare[][] squares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                assertEquals(i, squares[j][i].getColumn());
                assertEquals(j, squares[j][i].getRow());
                assertEquals(Color.WHITE, squares[j][i].getColor());
            }
        }

        assertEquals(TEST_STRING_1, name);
        assertEquals(TEST_ROW_1, rows);
        assertEquals(TEST_COL_1, columns);
        assertEquals(TEST_ROW_1*TEST_COL_1, size);
    }

    @Test
    // CASE: Constructing a graphghan where rows < columns
    public void graphghanConstructorTestWide() {
        String name = graphghan2.getName();
        int rows = graphghan2.getRows();
        int columns = graphghan2.getColumns();

        int size = graphghan2.getSize();

        GraphghanSquare[][] squares = graphghan2.getSquares();

        for (int i = 0; i < TEST_COL_2; i++) {
            for (int j = 0; j < TEST_ROW_2; j++) {
                assertEquals(i, squares[j][i].getColumn());
                assertEquals(j, squares[j][i].getRow());
                assertEquals(Color.WHITE, squares[j][i].getColor());
            }
        }

        assertEquals(TEST_STRING_2, name);
        assertEquals(TEST_ROW_2, rows);
        assertEquals(TEST_COL_2, columns);
        assertEquals(TEST_ROW_2*TEST_COL_2, size);
    }

    @Test
    // CASE: Constructing a graphghan where rows > columns
    public void graphghanConstructorTestNarrow() {
        String name = graphghan3.getName();
        int rows = graphghan3.getRows();
        int columns = graphghan3.getColumns();

        int size = graphghan3.getSize();

        GraphghanSquare[][] squares = graphghan3.getSquares();

        for (int i = 0; i < TEST_COL_3; i++) {
            for (int j = 0; j < TEST_ROW_3; j++) {
                assertEquals(i, squares[j][i].getColumn());
                assertEquals(j, squares[j][i].getRow());
                assertEquals(Color.WHITE, squares[j][i].getColor());
            }
        }

        assertEquals(TEST_STRING_3, name);
        assertEquals(TEST_ROW_3, rows);
        assertEquals(TEST_COL_3, columns);
        assertEquals(TEST_ROW_3*TEST_COL_3, size);
    }

    @Test
    // CASE: Change entire color of graphghan once WHITE -> BLACK
    public void changeColorEntireGraphghanSingle() {

        GraphghanSquare[][] originalSquares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                assertEquals(i, originalSquares[j][i].getColumn());
                assertEquals(j, originalSquares[j][i].getRow());
                assertEquals(Color.WHITE, originalSquares[j][i].getColor());
            }
        }
        graphghan1.changeColorEntireGraphghan(Color.BLACK);

        GraphghanSquare[][] changedSquares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                assertEquals(i, changedSquares[j][i].getColumn());
                assertEquals(j, changedSquares[j][i].getRow());
                assertEquals(Color.BLACK, changedSquares[j][i].getColor());
            }
        }

    }

    @Test
    // CASE: Change entire color of graphghan once WHITE -> BLACK -> DARK_MAGENTA
    public void changeColorEntireGraphghanMultiple() {

        GraphghanSquare[][] originalSquares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                assertEquals(i, originalSquares[j][i].getColumn());
                assertEquals(j, originalSquares[j][i].getRow());
                assertEquals(Color.WHITE, originalSquares[j][i].getColor());
            }
        }
        graphghan1.changeColorEntireGraphghan(Color.BLACK);

        GraphghanSquare[][] newSquares1 = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                assertEquals(i, newSquares1[j][i].getColumn());
                assertEquals(j, newSquares1[j][i].getRow());
                assertEquals(Color.BLACK, newSquares1[j][i].getColor());
            }
        }

        graphghan1.changeColorEntireGraphghan(Color.PINK);

        GraphghanSquare[][] newSquares2 = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                assertEquals(i, newSquares2[j][i].getColumn());
                assertEquals(j, newSquares2[j][i].getRow());
                assertEquals(Color.PINK, newSquares2[j][i].getColor());
            }
        }

    }

    @Test
    // CASE: Change color of single square in graphghan once WHITE -> BRIGHT_RED
    public void changeColorSingleSquareSingle() {
        GraphghanSquare controlSquare1 = graphghan1.getSquare(0,0);
        GraphghanSquare testSquare1 = graphghan1.getSquare(5,4);

        Color color1 = controlSquare1.getColor();
        Color color2 = testSquare1.getColor();

        graphghan1.changeColorSingleSquare(Color.PINK, 5,4);

        Color color3 = controlSquare1.getColor();
        Color color4 = testSquare1.getColor();

        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(Color.WHITE, color3);
        assertEquals(Color.PINK, color4);
    }

    @Test
    // CASE: Change color of single square in graphghan once WHITE -> BLACK -> DARK_RED
    public void changeColorSingleSquareMultiple() {
        GraphghanSquare controlSquare1 = graphghan1.getSquare(0,0);
        GraphghanSquare testSquare1 = graphghan1.getSquare(5,4);

        Color color1 = controlSquare1.getColor();
        Color color2 = testSquare1.getColor();

        graphghan1.changeColorSingleSquare(Color.BLACK, 5,4);

        Color color3 = controlSquare1.getColor();
        Color color4 = testSquare1.getColor();

        graphghan1.changeColorSingleSquare(Color.PINK, 5,4);

        Color color5 = controlSquare1.getColor();
        Color color6 = testSquare1.getColor();

        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(Color.WHITE, color3);
        assertEquals(Color.BLACK, color4);
        assertEquals(Color.WHITE, color5);
        assertEquals(Color.PINK, color6);
    }

    @Test
    // CASE: Change color of entire row once WHITE -> BRIGHT_RED
    public void changeColorEntireRowSingle() {
        GraphghanSquare[][] squares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (j == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

        graphghan1.changeColorEntireRow(Color.PINK, 5);

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (j == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.PINK, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

    }

    @Test
    // CASE: Change color of entire row many times WHITE -> BRIGHT_RED -> DARK_CYAN
    public void changeColorEntireRowMultiple() {
        GraphghanSquare[][] squares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (j == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

        graphghan1.changeColorEntireRow(Color.PINK, 5);

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (j == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.PINK, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

        graphghan1.changeColorEntireRow(Color.CYAN, 5);

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (j == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.CYAN, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

    }


    @Test
    // CASE: Change color of entire column once WHITE -> BRIGHT_RED
    public void changeColorEntireColumnSingle() {
        GraphghanSquare[][] squares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (i == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

        graphghan1.changeColorEntireColumn(Color.RED, 5);

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (i == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.RED, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

    }

    @Test
    // CASE: Change color of entire column many times WHITE -> BRIGHT_RED -> DARK_CYAN
    public void changeColorEntireColumnMultiple() {
        GraphghanSquare[][] squares = graphghan1.getSquares();

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (i == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

        graphghan1.changeColorEntireColumn(Color.RED, 5);

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (i == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.RED, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

        graphghan1.changeColorEntireColumn(Color.CYAN, 5);

        for (int i = 0; i < TEST_COL_1; i++) {
            for (int j = 0; j < TEST_ROW_1; j++) {
                if (i == 5) {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.CYAN, squares[j][i].getColor());
                }
                else {
                    assertEquals(i, squares[j][i].getColumn());
                    assertEquals(j, squares[j][i].getRow());
                    assertEquals(Color.WHITE, squares[j][i].getColor());
                }
            }
        }

        graphghan1.changeColorEntireRow(Color.CYAN, 5);

    }

    @Test

    public void testToString() {
        String string = graphghan1.toString();
        assertEquals(TEST_STRING_1, string);
    }

}
