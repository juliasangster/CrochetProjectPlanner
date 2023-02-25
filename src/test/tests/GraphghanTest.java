package tests;

import model.Color;
import model.Graphghan;
import model.GraphghanSquare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CLASS COMMENT:

public class GraphghanTest {

    final static String TEST_STRING_1 = "Test";
    final static String TEST_STRING_2 = "WideBlanket";
    final static String TEST_STRING_3 = "NarrowBlanket";

    final static int TEST_ROW_1 = 10;
    final static int TEST_COL_1 = 10;

    final static int TEST_ROW_2 = 10;
    final static int TEST_COL_2 = 15;

    final static int TEST_ROW_3 = 20;
    final static int TEST_COL_3 = 10;


    Graphghan graphghan1;
    Graphghan graphghan2;
    Graphghan graphghan3;

    @BeforeEach

    void graphghanTestSetup() {
        graphghan1 = new Graphghan(TEST_STRING_1, TEST_ROW_1, TEST_COL_1);
        graphghan2 = new Graphghan(TEST_STRING_2, TEST_ROW_2, TEST_COL_2);
        graphghan3 = new Graphghan(TEST_STRING_3, TEST_ROW_3, TEST_COL_3);
    }

    @Test

    void graphghanConstructorTestSquare() {
        String name = graphghan1.getName();
        int rows = graphghan1.getRows();
        int columns = graphghan1.getColumns();

        int size = graphghan1.getSize();

        GraphghanSquare first = graphghan1.getSquare(0,0);
        GraphghanSquare last = graphghan1.getSquare(TEST_ROW_1-1,TEST_COL_1-1);

        Color color1 = first.getColor();
        Color color2 = last.getColor();

        int rowIndex1 = first.getRow();
        int colIndex1 = first.getColumn();

        int rowIndex2 = last.getRow();
        int colIndex2 = last.getColumn();

        assertEquals(TEST_STRING_1, name);
        assertEquals(TEST_ROW_1, rows);
        assertEquals(TEST_COL_1, columns);
        assertEquals(TEST_ROW_1*TEST_COL_1, size);
        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(0, rowIndex1);
        assertEquals(0, colIndex1);
        assertEquals(TEST_ROW_1-1, rowIndex2);
        assertEquals(TEST_COL_1-1, colIndex2);


    }

    @Test

    void graphghanConstructorTestWide() {
        String name = graphghan2.getName();
        int rows = graphghan2.getRows();
        int columns = graphghan2.getColumns();

        int size = graphghan2.getSize();

        GraphghanSquare first = graphghan2.getSquare(0,0);
        GraphghanSquare last = graphghan2.getSquare(TEST_ROW_2-1,TEST_COL_2-1);

        Color color1 = first.getColor();
        Color color2 = last.getColor();

        int rowIndex1 = first.getRow();
        int colIndex1 = first.getColumn();

        int rowIndex2 = last.getRow();
        int colIndex2 = last.getColumn();

        assertEquals(TEST_STRING_2, name);
        assertEquals(TEST_ROW_2, rows);
        assertEquals(TEST_COL_2, columns);
        assertEquals(TEST_ROW_2*TEST_COL_2, size);
        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(0, rowIndex1);
        assertEquals(0, colIndex1);
        assertEquals(TEST_ROW_2-1, rowIndex2);
        assertEquals(TEST_COL_2-1, colIndex2);
    }

    @Test

    void graphghanConstructorTestNarrow() {
        String name = graphghan3.getName();
        int rows = graphghan3.getRows();
        int columns = graphghan3.getColumns();

        int size = graphghan3.getSize();

        GraphghanSquare first = graphghan3.getSquare(0,0);
        GraphghanSquare last = graphghan3.getSquare(TEST_ROW_3-1,TEST_COL_3-1);

        Color color1 = first.getColor();
        Color color2 = last.getColor();

        int rowIndex1 = first.getRow();
        int colIndex1 = first.getColumn();

        int rowIndex2 = last.getRow();
        int colIndex2 = last.getColumn();

        assertEquals(TEST_STRING_3, name);
        assertEquals(TEST_ROW_3, rows);
        assertEquals(TEST_COL_3, columns);
        assertEquals(TEST_ROW_3*TEST_COL_3, size);
        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(0, rowIndex1);
        assertEquals(0, colIndex1);
        assertEquals(TEST_ROW_3-1, rowIndex2);
        assertEquals(TEST_COL_3-1, colIndex2);
    }

    @Test

    void changeColorEntireGraphghanSingle() {
        GraphghanSquare first = graphghan1.getSquare(0,0);
        GraphghanSquare last = graphghan1.getSquare(TEST_ROW_1-1,TEST_COL_1-1);

        Color color1 = first.getColor();
        Color color2 = last.getColor();

        graphghan1.changeColorEntireGraphghan(Color.BLACK);

        GraphghanSquare first2 = graphghan1.getSquare(0,0);
        GraphghanSquare last2 = graphghan1.getSquare(TEST_ROW_1-1,TEST_COL_1-1);
        Color color3 = first2.getColor();
        Color color4 = last2.getColor();

        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(Color.BLACK, color3);
        assertEquals(Color.BLACK, color4);

    }

    @Test

    void changeColorEntireGraphghanMultiple() {
        GraphghanSquare first = graphghan1.getSquare(0,0);
        GraphghanSquare last = graphghan1.getSquare(TEST_ROW_1-1,TEST_COL_1-1);

        Color color1 = first.getColor();
        Color color2 = last.getColor();

        graphghan1.changeColorEntireGraphghan(Color.BLACK);

        GraphghanSquare first2 = graphghan1.getSquare(0,0);
        GraphghanSquare last2 = graphghan1.getSquare(TEST_ROW_1-1,TEST_COL_1-1);

        Color color3 = first2.getColor();
        Color color4 = last2.getColor();

        graphghan1.changeColorEntireGraphghan(Color.DARK_MAGENTA);

        GraphghanSquare first3 = graphghan1.getSquare(0,0);
        GraphghanSquare last3 = graphghan1.getSquare(TEST_ROW_1-1,TEST_COL_1-1);

        Color color5 = first3.getColor();
        Color color6 = last3.getColor();

        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(Color.BLACK, color3);
        assertEquals(Color.BLACK, color4);
        assertEquals(Color.DARK_MAGENTA, color5);
        assertEquals(Color.DARK_MAGENTA, color6);

    }

    @Test

    void changeColorSingleSquareSingle() {
        GraphghanSquare controlSquare1 = graphghan1.getSquare(0,0);
        GraphghanSquare testSquare1 = graphghan1.getSquare(5,4);

        Color color1 = controlSquare1.getColor();
        Color color2 = testSquare1.getColor();

        graphghan1.changeColorSingleSquare(Color.BRIGHT_RED, 5,4);

        Color color3 = controlSquare1.getColor();
        Color color4 = testSquare1.getColor();

        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(Color.WHITE, color3);
        assertEquals(Color.BRIGHT_RED, color4);
    }

    @Test

    void changeColorEntireRowSingle() {
        GraphghanSquare controlSquare1 = graphghan1.getSquare(0,0);
        GraphghanSquare testSquare1 = graphghan1.getSquare(5,0);
        GraphghanSquare testSquare2 = graphghan1.getSquare(5,9);

        Color color1 = controlSquare1.getColor();
        Color color2 = testSquare1.getColor();
        Color color3 = testSquare2.getColor();

        graphghan1.changeColorEntireRow(Color.BRIGHT_RED, 5);

        Color color4 = controlSquare1.getColor();
        Color color5 = testSquare1.getColor();
        Color color6 = testSquare2.getColor();

        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(Color.WHITE, color3);
        assertEquals(Color.WHITE, color4);
        assertEquals(Color.BRIGHT_RED, color5);
        assertEquals(Color.BRIGHT_RED, color6);
    }

    @Test

    void changeColorEntireColumnSingle() {
        GraphghanSquare controlSquare1 = graphghan1.getSquare(0,0);
        GraphghanSquare testSquare1 = graphghan1.getSquare(0,4);
        GraphghanSquare testSquare2 = graphghan1.getSquare(9,4);

        Color color1 = controlSquare1.getColor();
        Color color2 = testSquare1.getColor();
        Color color3 = testSquare2.getColor();

        graphghan1.changeColorEntireColumn(Color.BRIGHT_RED, 4);

        Color color4 = controlSquare1.getColor();
        Color color5 = testSquare1.getColor();
        Color color6 = testSquare2.getColor();

        assertEquals(Color.WHITE, color1);
        assertEquals(Color.WHITE, color2);
        assertEquals(Color.WHITE, color3);
        assertEquals(Color.WHITE, color4);
        assertEquals(Color.BRIGHT_RED, color5);
        assertEquals(Color.BRIGHT_RED, color6);
    }

}
