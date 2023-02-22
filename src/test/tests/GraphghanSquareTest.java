package tests;

import model.Color;
import model.GraphghanSquare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphghanSquareTest {

    final static int TEST_ROW_1 = 0;
    final static int TEST_ROW_2 = 100;
    final static int TEST_COLUMN_1 = 0;
    final static int TEST_COLUMN_2 = 100;


    GraphghanSquare graphghanSquare1;
    GraphghanSquare graphghanSquare2;

    @BeforeEach

    void graphghanSquareTestSetup() {
        graphghanSquare1 = new GraphghanSquare(TEST_ROW_1,TEST_COLUMN_1);
        graphghanSquare2 = new GraphghanSquare(TEST_ROW_2, TEST_COLUMN_2);
    }

    @Test

    void graphghanSquareConstructorTest() {

        assertEquals(TEST_ROW_1, graphghanSquare1.getRow());
        assertEquals(TEST_COLUMN_1, graphghanSquare1.getColumn());
        assertEquals(Color.WHITE, graphghanSquare1.getColor());

        assertEquals(TEST_ROW_2, graphghanSquare2.getRow());
        assertEquals(TEST_COLUMN_2, graphghanSquare2.getColumn());
        assertEquals(Color.WHITE, graphghanSquare2.getColor());

    }

    @Test

    void changeColorTestSingle() {
        Color originalColor = graphghanSquare1.getColor();
        graphghanSquare1.changeColor(Color.BLACK);
        Color newColor = graphghanSquare1.getColor();
        assertEquals(Color.WHITE, originalColor);
        assertEquals(Color.BLACK, newColor);
    }

    @Test

    void changeColorTestMultiple() {
        Color originalColor = graphghanSquare1.getColor();
        graphghanSquare1.changeColor(Color.BLACK);
        Color newColor1 = graphghanSquare1.getColor();
        graphghanSquare1.changeColor(Color.DARK_BLUE);
        Color newColor2 = graphghanSquare1.getColor();

        assertEquals(Color.WHITE, originalColor);
        assertEquals(Color.BLACK, newColor1);
        assertEquals(Color.DARK_BLUE, newColor2);
    }

    @Test

    void changeColorTestMultipleToWhite() {
        Color originalColor = graphghanSquare1.getColor();
        graphghanSquare1.changeColor(Color.BLACK);
        Color newColor1 = graphghanSquare1.getColor();
        graphghanSquare1.changeColor(Color.WHITE);
        Color newColor2 = graphghanSquare1.getColor();

        assertEquals(Color.WHITE, originalColor);
        assertEquals(Color.BLACK, newColor1);
        assertEquals(Color.WHITE, newColor2);
    }


    @Test

    void isGivenColorTestWhite() {
        assertTrue(graphghanSquare1.isGivenColor(Color.WHITE));
    }

}
