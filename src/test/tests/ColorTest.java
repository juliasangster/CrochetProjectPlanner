package tests;

import model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CLASS COMMENT: Testing suite for model.Color class

public class ColorTest {

    private Color black;
    private Color darkRed;
    private Color darkGreen;
    private Color darkYellow;
    private Color darkBlue;
    private Color darkMagenta;
    private Color darkCyan;
    private Color darkWhite;
    private Color brightBlack;
    private Color brightRed;
    private Color brightGreen;
    private Color brightYellow;
    private Color brightBlue;
    private Color brightMagenta;
    private Color brightCyan;
    private Color white;

    @BeforeEach

    public void colorTestSetup() {
        black = Color.BLACK;
        darkRed = Color.DARK_RED;
        darkGreen = Color.DARK_GREEN;
        darkYellow = Color.DARK_YELLOW;
        darkBlue = Color.DARK_BLUE;
        darkMagenta = Color.DARK_MAGENTA;
        darkCyan = Color.DARK_CYAN;
        darkWhite = Color.DARK_WHITE;
        brightBlack = Color.BRIGHT_BLACK;
        brightRed = Color.BRIGHT_RED;
        brightGreen = Color.BRIGHT_GREEN;
        brightYellow = Color.BRIGHT_YELLOW;
        brightBlue = Color.BRIGHT_BLUE;
        brightMagenta = Color.BRIGHT_MAGENTA;
        brightCyan = Color.BRIGHT_CYAN;
        white = Color.WHITE;
    }

    @Test
    // CASE: getColorCode() called on Color.BLACK
    public void getColorCodeTestBlack() {
        assertEquals("\u001b[30m",
                black.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_RED
    public void getColorCodeTestDarkRed() {
        assertEquals("\u001b[31m",
                darkRed.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_GREEN
    public void getColorCodeTestDarkGreen() {
        assertEquals("\u001b[32m",
                darkGreen.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_YELLOW
    public void getColorCodeTestDarkYellow() {
        assertEquals("\u001b[33m",
                darkYellow.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.DARK_BLUE
    public void getColorCodeTestDarkBlue() {
        assertEquals("\u001b[34m",
                darkBlue.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.DARK_MAGENTA
    public void getColorCodeTestDarkMagenta() {
        assertEquals("\u001b[35m",
                darkMagenta.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.DARK_CYAN
    public void getColorCodeTestDarkCyan() {
        assertEquals("\u001b[36m",
                darkCyan.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.DARK_WHITE
    public void getColorCodeTestDarkWhite() {
        assertEquals("\u001b[37m",
                darkWhite.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_BLACK
    public void getColorCodeTestBrightBlack() {
        assertEquals("\u001b[90m",
                brightBlack.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_RED
    public void getColorCodeTestBrightRed() {
        assertEquals("\u001b[91m",
                brightRed.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_GREEN
    public void getColorCodeTestBrightGreen() {
        assertEquals("\u001b[92m",
                brightGreen.getColorCode());

    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_YELLOW
    public void getColorCodeTestBrightYellow() {
        assertEquals("\u001b[93m",
                brightYellow.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_BLUE
    public void getColorCodeTestBrightBlue() {
        assertEquals("\u001b[94m",
                brightBlue.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_MAGENTA
    public void getColorCodeTestBrightMagenta() {
        assertEquals("\u001b[95m",
                brightMagenta.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.BRIGHT_CYAN
    public void getColorCodeTestBrightCyan() {
        assertEquals("\u001b[96m",
                brightCyan.getColorCode());
    }

    @Test
    // CASE: getColorCode() called on Color.WHITE
    public void getColorCodeTestWhite() {
        assertEquals("\u001b[97m",
                white.getColorCode());
    }

}
